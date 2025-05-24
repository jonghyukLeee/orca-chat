package com.orca.chat.repository

import com.orca.chat.domain.ChatRoom
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface ChatRoomRepository: ReactiveMongoRepository<ChatRoom, ObjectId> {
}