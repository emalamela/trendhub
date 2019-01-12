package com.chimichanga.trendhub.repository.util

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan

/**
 * Returns a [Spannable] that bolds the repository name part of the repository full name.
 *
 * For example, in `Kotlin/anko` the result would have the `anko` part in bold.
 */
fun formatRepositoryName(repositoryFullName: String): Spannable {
    val nameSplit = repositoryFullName.split("/")
    val nameToSpan = "${nameSplit[0]} / ${nameSplit[1]}"
    return SpannableString(nameToSpan).apply {
        setSpan(
            StyleSpan(Typeface.BOLD),
            nameToSpan.lastIndexOf(' '), nameToSpan.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
}