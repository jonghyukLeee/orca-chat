package com.orca.chat.api

import com.orca.chat.domain.toResponse
import com.orca.chat.service.ChatService
import com.orca.chat.util.baseResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller

@Tag(name = "Chat", description = "Chat APIs")
@Controller
class ChatController(
    private val chatService: ChatService
) {
    @Operation(
        summary = "채팅 전송",
        description = "채팅 메시지 전송 API"
    )
    @MessageMapping("/{roomId}/send-message")
    @SendTo("/topic")
    suspend fun sendMessage(
        @DestinationVariable roomId: String,
        @Payload request: SendMessageRequest
    ): ResponseEntity<ChatMessageResponse> {
        return baseResponse(
            body = chatService.sendMessage(request.toCommand()).toResponse()
        )
    }
}