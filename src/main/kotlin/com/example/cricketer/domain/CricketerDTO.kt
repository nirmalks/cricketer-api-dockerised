package com.example.cricketer.domain

data class CricketerDTO(
    val id: Long?,
    val name: String,
    val country: String,
    val highestScore: Number
) {
    companion object {
        fun toDto(cricketer: Cricketer): CricketerDTO {
            return with(cricketer) {
                CricketerDTO(
                    id,
                    name,
                    country,
                    highestScore
                )
            }
        }
    }
}