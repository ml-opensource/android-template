package com.monstarlab.core.network.errorhandling.extensions

fun String.without(value: String) = replace(value, "")

val String.withoutSpaces: String get() = without(" ")
