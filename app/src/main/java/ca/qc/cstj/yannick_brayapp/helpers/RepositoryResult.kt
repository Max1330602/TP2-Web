package ca.qc.cstj.yannick_brayapp.helpers

import java.lang.Exception

sealed class RepositoryResult<out R> {
    data class Success<out T>(val data:T) : RepositoryResult<T>()
    data class Error(val exception: Exception):RepositoryResult<Nothing>()
}