package com.orca.chat.exception

import com.orca.chat.util.getCurrentTimestamp
import org.springframework.http.HttpStatusCode

class BaseException(
    val httpStatus: HttpStatusCode,
    val code: String,
    override val message: String,
) : RuntimeException() {

    val timeStamp = getCurrentTimestamp()

    constructor(e: ErrorCode) : this(
        httpStatus = e.httpStatus,
        code = e.name,
        message = e.message,
    )
}