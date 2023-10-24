package com.mpersand.presentation.viewmodel.util

import com.mpersand.domain.exception.BadRequestException
import com.mpersand.domain.exception.ConflictException
import com.mpersand.domain.exception.ForbiddenException
import com.mpersand.domain.exception.NoContentException
import com.mpersand.domain.exception.NotFoundException
import com.mpersand.domain.exception.ServerException
import com.mpersand.domain.exception.TimeOutException
import com.mpersand.domain.exception.TokenExpiredException
import com.mpersand.domain.exception.UnauthorizedException

suspend fun Throwable.exceptionHandling(
    noContentAction: () -> Unit = {},
    badRequestAction: () -> Unit = {},
    unauthorizedAction: suspend () -> Unit = {},
    forbiddenAction: () -> Unit = {},
    notFoundAction: () -> Unit = {},
    timeOutAction: () -> Unit = {},
    conflictAction: () -> Unit = {},
    serverAction: () -> Unit = {},
    unknownAction: () -> Unit = {},
) {
    when (this) {
        is NoContentException -> noContentAction()
        is BadRequestException -> badRequestAction()
        is UnauthorizedException, is TokenExpiredException -> unauthorizedAction()
        is ForbiddenException -> forbiddenAction()
        is NotFoundException -> notFoundAction()
        is TimeOutException -> timeOutAction()
        is ConflictException -> conflictAction()
        is ServerException -> serverAction()
        else -> unknownAction()
    }
}
