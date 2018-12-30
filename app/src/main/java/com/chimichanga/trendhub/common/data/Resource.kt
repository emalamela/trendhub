package com.chimichanga.trendhub.common.data

/**
 * Represents any resource of type [T] that has to be retrieved through a asynchronous operation.
 */
sealed class Resource<T : Any>

/**
 * Represents a [Resource] in a loading state.
 */
class Loading<T : Any> : Resource<T>()

/**
 * Represents a [Resource] which errored out when retrieving it. Optionally carries a [payload] of type [E].
 *
 * @param E type of the optional [payload]
 */
class Error<T : Any, E : Any>(val payload: E? = null) : Resource<T>()

/**
 * Represents a [Resource] which was successfully retrieved. Carries [data] of type [T].
 */
class Success<T : Any>(val data: T) : Resource<T>()