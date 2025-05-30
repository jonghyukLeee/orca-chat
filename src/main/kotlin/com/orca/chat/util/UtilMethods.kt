package com.orca.chat.util

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.orca.chat.exception.BaseException
import com.orca.chat.exception.ErrorCode
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

fun convertMatchDateFormant(date: String, time: String): Instant {
    val dateTimeString = "$date $time"

    return try {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        LocalDateTime.parse(dateTimeString, formatter).atZone(ZoneId.of("Asia/Seoul")).toInstant()
    } catch (e: Exception) {
        throw BaseException(ErrorCode.PARSING_EXCEPTION)
    }
}

fun Instant.toKstString(): String {
    return this.atZone(ZoneId.of("Asia/Seoul"))
        .format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분"))
}

fun getCurrentTimestamp(): String {
    return LocalDateTime.now()
        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
}

fun <T> baseResponse(status: HttpStatusCode = HttpStatus.OK, body: T): ResponseEntity<T> {
    return ResponseEntity.status(status).body(body)
}

fun buildQueryById(id: ObjectId): Query {
    return Query(Criteria.where("_id").`is`(id))
}

fun generateTransactionId(): String {
    return "txId:${UUID.randomUUID()}"
}

fun Any.toJsonString(): String {
    return ObjectMapper().writeValueAsString(this)
}

fun String.getJsonValue(key: String): String? {
    val mapper = jacksonObjectMapper()
    val node: JsonNode = mapper.readTree(this)
    return node[key]?.asText()
}