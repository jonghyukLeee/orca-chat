package com.orca.chat.api

import com.orca.chat.service.SendMessageCommand
import io.swagger.v3.oas.annotations.media.Schema
import org.bson.types.ObjectId

@Schema(description = "채팅 전송 RequestDTO")
data class SendMessageRequest(
    @field:Schema(description = "ChatRoom ID")
    val roomId: ObjectId? = null,

    @field:Schema(description = "송신자 ID")
    val senderId: ObjectId,

    @field:Schema(description = "송신자 이름")
    val senderName: String,

    @field:Schema(description = "수신자 ID")
    val receiverId: ObjectId,

    @field:Schema(description = "채팅 내용")
    val content: String,
) {
    fun toCommand(): SendMessageCommand {
        return SendMessageCommand(
            roomId = this.roomId,
            senderId = this.senderId,
            senderName = this.senderName,
            receiverId = this.receiverId,
            content = this.content,
        )
    }
}

@Schema(description = "채팅 메시지 ResponseDTO")
data class ChatMessageResponse(
    @field:Schema(description = "ChatMessage ID")
    val id: String,

    @field:Schema(description = "ChatRoom ID")
    val roomId: String,

    @field:Schema(description = "송신자 ID")
    val senderId: String,

    @field:Schema(description = "송신자 이름")
    val senderName: String,

    @field:Schema(description = "수신자 ID")
    val receiverId: String,

    @field:Schema(description = "채팅 내용")
    val content: String,

    @field:Schema(description = "생성 일자 (KST)")
    val createdAt: String
)