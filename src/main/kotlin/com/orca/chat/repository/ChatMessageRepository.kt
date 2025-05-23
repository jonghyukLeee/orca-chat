package com.orca.chat.repository

import com.orca.chat.domain.ChatMessage
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface ChatMessageRepository: ReactiveMongoRepository<ChatMessage, ObjectId> {
}