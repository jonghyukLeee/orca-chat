package com.orca.chat

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ChatApplication

suspend fun main(args: Array<String>) {
	runApplication<ChatApplication>(*args)
}
