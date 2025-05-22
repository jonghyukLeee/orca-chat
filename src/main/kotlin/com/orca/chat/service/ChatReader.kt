package com.orca.chat.service

import com.orca.chat.domain.ChatRoom
import com.orca.chat.repository.ChatRoomRepository
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.bson.types.ObjectId
import org.springframework.stereotype.Repository

@Repository
class ChatReader(
    private val chatRoomRepository: ChatRoomRepository
) {
    suspend fun findOneById(roomId: ObjectId): ChatRoom? {
        return chatRoomRepository.findById(roomId).awaitSingleOrNull()
    }
}