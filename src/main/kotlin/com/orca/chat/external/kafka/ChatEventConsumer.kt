package com.orca.chat.external.kafka

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.orca.chat.domain.RoomType
import com.orca.chat.external.redis.RedisService
import com.orca.chat.service.ChatService
import org.bson.types.ObjectId
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class ChatEventConsumer(
    private val chatService: ChatService,
    private val objectMapper: ObjectMapper,
    private val redisService: RedisService,
    private val eventPublisher: EventPublisher
) {

    @KafkaListener(topics = [EventTopics.CLUB_CREATED])
    suspend fun clubCreated(txId: String) {
        val jsonString = redisService.get(txId)
        try {
            val message = parseToMessage<ClubCreatedMessage>(jsonString)
            chatService.createRoom(type = RoomType.CLUB, clubId = ObjectId(message.clubId))
        } catch (e: Exception) {
            eventPublisher.send(EventTopics.CLUB_CREATE_FAILED, jsonString)
        }
    }

    private suspend inline fun <reified T> parseToMessage(jsonString: String): T {
        return objectMapper.readValue<T>(jsonString)
    }
}