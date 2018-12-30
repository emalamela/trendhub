package com.chimichanga.trendhub.common.pagination

data class Page<T : Any>(val items: List<T>)