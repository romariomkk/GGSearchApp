package com.romariomkk.ggsearch.util.validator

interface ValidityChecker<T, R> {

    fun checkValidity(item: T): R
}