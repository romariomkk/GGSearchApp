package com.romariomkk.ggsearch.util

class Resource<out T> private constructor(val status: Status, val data: T?, val exception: Throwable?) {

    enum class Status { LOADING, SUCCESS, ERROR, ABORT }

    companion object {

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }

        fun <T> success(data: T? = null): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(exception: Throwable? = null, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, exception)
        }

        fun <T> abort(exception: Throwable? = null, data: T? = null): Resource<T> {
            return Resource(Status.ABORT, data, exception)
        }

    }
}