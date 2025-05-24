package com.orca.chat.external.kafka

data class ClubCreatedMessage(
    val clubId: String,
    val playerId: String
)