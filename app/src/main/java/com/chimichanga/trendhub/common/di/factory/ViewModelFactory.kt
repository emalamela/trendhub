package com.chimichanga.trendhub.common.di.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

/**
 * Backed by `Dagger`s Multibinding [Map], this factory [create]s [ViewModel] instances by retrieving its corresponding
 * [Provider] instance and executing it.
 */
@Singleton
class ViewModelFactory
@Inject constructor(
    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    /**
     * Creates a [ViewModel] instance of type [T] by finding its corresponding [Provider]
     *
     * @param T the type of [ViewModel] to create
     * @param modelClass the [Class] of the type [T]
     *
     * @return an instance of [T]
     *
     * @throws IllegalArgumentException if no [Provider] is found for such [T]
     *
     * @see [com.chimichanga.trendhub.common.di.module.ViewModelFactoryModule]
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = creators[modelClass] ?:
        creators.asIterable().firstOrNull { modelClass.isAssignableFrom(it.key) }?.value
        ?: throw IllegalArgumentException("Unknown model class $modelClass")

        return try {
            @Suppress("UNCHECKED_CAST")
            creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }
}