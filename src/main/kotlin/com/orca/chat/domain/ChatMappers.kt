package com.orca.chat.domain

import com.orca.chat.api.ChatMessageResponse
import com.orca.chat.util.toKstString

fun ChatMessage.toResponse(): ChatMessageResponse {
    return ChatMessageResponse(
        id = this.id.toString(),
        roomId = this.roomId.toString(),
        senderId = this.senderName,
        senderName = this.senderName,
        receiverId = this.receiverId.toString(),
        content = this.content,
        createdAt = this.createdAt!!.toKstString(),
    )
}