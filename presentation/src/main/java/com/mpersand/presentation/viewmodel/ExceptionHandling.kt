package com.mpersand.presentation.viewmodel

import com.mpersand.domain.exception.BadRequestException
import com.mpersand.domain.exception.ConflictException
import com.mpersand.domain.exception.ForbiddenException
import com.mpersand.domain.exception.NotFoundException
import com.mpersand.domain.exception.ServerException
import com.mpersand.domain.exception.TimeOutException
import com.mpersand.domain.exception.TokenExpiredException
import com.mpersand.domain.exception.UnauthorizedException

fun Throwable.exceptionHandling(
    badRequestAction: () -> Unit = {},
    unauthorizedAction: () -> Unit = {},
    forbiddenAction: () -> Unit = {},
    notFoundAction: () -> Unit = {},
    timeOutAction: () -> Unit = {},
    conflictAction: () -> Unit = {},
    serverAction: () -> Unit = {},
    unknownAction: () -> Unit = {},
) {
    when (this) {
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