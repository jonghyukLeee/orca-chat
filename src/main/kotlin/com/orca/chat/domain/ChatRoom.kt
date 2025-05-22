package com.orca.chat.domain

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(collection = "chat_rooms")
data class ChatRoom(
    val id: ObjectId? = null,
    val type: RoomType,
    val clubId: ObjectId? = null,
    val participants: List<ObjectId>,
    val lastMessage: LastMessage? = null
): Auditable()

enum class RoomType {
    ONE_TO_ONE,
    CLUB
}

data class LastMessage(
    val messageId: ObjectId,
    val senderId: ObjectId,
    val senderName: String,
    val content: String,
    val timestamp: Instant
)