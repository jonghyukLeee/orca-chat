package com.orca.chat.domain

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "chat_messages")
data class ChatMessage(
    @Id
    val id: ObjectId? = null,
    val roomId: ObjectId,
    val senderId: ObjectId,
    val senderName: String,
    val receiverId: ObjectId,
    val content: String
) : Auditable() {
    fun toLastMessage(): LastMessage {
        return LastMessage(
            messageId = this.id!!,
            senderId = this.senderId,
            senderName = this.senderName,
            content = this.content,
            timestamp = this.createdAt!!,
        )
    }
}