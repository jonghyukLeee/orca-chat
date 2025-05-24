package com.orca.chat.service

import com.orca.chat.domain.ChatMessage
import com.orca.chat.domain.ChatRoom
import com.orca.chat.domain.LastMessage
import com.orca.chat.domain.RoomType
import com.orca.chat.repository.ChatMessageRepository
import com.orca.chat.repository.ChatRoomRepository
import com.orca.chat.util.buildQueryById
import kotlinx.coroutines.reactor.awaitSingle
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.FindAndModifyOptions
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Repository

@Repository
class ChatManager(
    private val chatMessageRepository: ChatMessageRepository,
    private val chatRoomRepository: ChatRoomRepository,
    private val reactiveMongoTemplate: ReactiveMongoTemplate
) {
    suspend fun createRoom(
        type: RoomType,
        clubId: ObjectId?,
        participants: List<ObjectId>
    ): ChatRoom {
        return chatRoomRepository.save(
            ChatRoom(
                type = type,
                clubId = clubId,
                participants = participants
            )
        ).awaitSingle()
    }

    suspend fun createMessage(roomId: ObjectId, command: SendMessageCommand): ChatMessage {
        return chatMessageRepository.save(
            ChatMessage(
                roomId = roomId,
                senderId = command.senderId,
                senderName = command.senderName,
                receiverId = command.receiverId,
                content = command.content,
            )
        ).awaitSingle()
    }

    suspend fun updateLastMessage(roomId: ObjectId, newMessage: LastMessage) {
        val update = Update().apply {
            set("lastMessage", newMessage)
        }

        reactiveMongoTemplate.findAndModify(
            buildQueryById(roomId),
            update,
            FindAndModifyOptions().returnNew(true),
            ChatRoom::class.java
        ).awaitSingle()
    }
}