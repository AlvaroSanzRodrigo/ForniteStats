package io.github.alvarosanzrodrigo.fornitestatsapp.Models

data class Stat (
    val label: String,
    val field: String,
    val category: String,
    val valueDec: Double,
    val value: String,
    val rank: Long,
    val percentile: Double,
    val displayValue: String
)
