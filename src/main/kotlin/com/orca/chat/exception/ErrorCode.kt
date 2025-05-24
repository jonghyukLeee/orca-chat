package com.orca.chat.exception

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode

enum class ErrorCode(val httpStatus: HttpStatusCode = HttpStatus.NOT_FOUND, val message: String) {
    UNDEFINED_EXCEPTION(httpStatus = HttpStatus.INTERNAL_SERVER_ERROR, message = "Sorry, undefined exception"),
    BAD_REQUEST(httpStatus = HttpStatus.BAD_REQUEST, message = "Bad request. check API documents."),

    PARSING_EXCEPTION(httpStatus = HttpStatus.BAD_REQUEST, message = "String to Date parsing failed. check API documents"),

    CHAT_ROOM_NOT_FOUND(message = "Chat room not found."),

    // external
    REDIS_KEY_NOT_FOUND(message = "Key does not exist in Redis"),
    JSON_KEY_NOT_FOUND(message = "Invalid json key"),
}