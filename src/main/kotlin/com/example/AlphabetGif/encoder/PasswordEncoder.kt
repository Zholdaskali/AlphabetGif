package com.example.AlphabetGif.encoder

interface PasswordEncoder {

    fun hash(password: String): String

    fun check(password: String, hash: String): Boolean

}