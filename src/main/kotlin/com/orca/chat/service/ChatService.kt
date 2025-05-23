package com.orca.chat.service

import com.orca.chat.domain.ChatMessage
import com.orca.chat.domain.ChatRoom
import com.orca.chat.domain.RoomType
import com.orca.chat.exception.BaseException
import com.orca.chat.exception.ErrorCode
import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class ChatService(
    private val chatReader: ChatReader,
    private val chatManager: ChatManager
) {
    suspend fun sendMessage(command: SendMessageCommand): ChatMessage {
        val room = if (command.roomId == null) {
            createRoom(type = RoomType.ONE_TO_ONE, participants = mutableListOf(command.senderId, command.receiverId))
        } else {
            getRoomById(command.roomId) ?: throw BaseException(ErrorCode.CHAT_ROOM_NOT_FOUND)
        }

        val message = chatManager.createMessage(roomId = command.roomId ?: room.id!!, command)

        updateLastMessage(message)
        return message
    }

    suspend fun createRoom(
        type: RoomType,
        clubId: ObjectId? = null,
        participants: List<ObjectId> = mutableListOf()
    ): ChatRoom {
        return chatManager.createRoom(
            type = type,
            clubId = clubId,
            participants = participants
        )
    }

    suspend fun getRoomById(roomId: ObjectId): ChatRoom? {
        return chatReader.findOneById(roomId)
    }

    suspend fun updateLastMessage(message: ChatMessage) {
        chatManager.updateLastMessage(
            message.roomId,
            message.toLastMessage()
        )
    }
}