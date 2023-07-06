package com.mpersand.presentation.viewmodel.util

suspend fun <T, R> combineResults(
    suspendFun1: suspend () -> Result<T>,
    suspendFun2: suspend () -> Result<R>
): Result<Pair<T, R>> {
    val result1 = suspendFun1()
    val result2 = suspendFun2()

    return if (result1.isSuccess && result2.isSuccess) {
        val pair = Pair(result1.getOrThrow(), result2.getOrThrow())
        Result.success(pair)
    } else {
        val error = result1.exceptionOrNull() ?: result2.exceptionOrNull()
        Result.failure(error!!)
    }
}