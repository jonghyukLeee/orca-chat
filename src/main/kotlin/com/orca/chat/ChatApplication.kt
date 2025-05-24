package com.orca.chat

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication
class ChatApplication

suspend fun main(args: Array<String>) {
	runApplication<ChatApplication>(*args)
}
