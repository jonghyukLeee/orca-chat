package com.orca.chat.service

import org.bson.types.ObjectId

data class SendMessageCommand(
    val roomId: ObjectId?,
    val senderId: ObjectId,
    val senderName: String,
    val receiverId: ObjectId,
    val content: String,
)