package io.github.alvarosanzrodrigo.fornitestatsapp.Models

data class Player (
    val accountID: String,
    val platformID: Long,
    val platformName: String,
    val platformNameLong: String,
    val epicUserHandle: String,
    val stats: PlayerStats
)
