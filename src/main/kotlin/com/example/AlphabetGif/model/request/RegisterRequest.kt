package com.example.AlphabetGif.model.request

data class RegisterRequest(
    val password: String,
    val userName: String,
    val userPhone: String
)