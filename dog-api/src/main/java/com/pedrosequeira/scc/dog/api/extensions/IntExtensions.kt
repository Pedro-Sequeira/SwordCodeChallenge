package com.pedrosequeira.scc.dog.api.extensions

fun Int?.orZero(): Int {
    return this ?: 0
}
