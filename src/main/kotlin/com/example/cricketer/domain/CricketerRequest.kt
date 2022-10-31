package com.example.cricketer.domain

import javax.validation.constraints.NotEmpty

class CricketerRequest(
    @NotEmpty(message = "Name cannot be empty")
    val name: String,
    @NotEmpty(message = "Country cannot be empty")
    val country: String,
    val highestScore: Number
)