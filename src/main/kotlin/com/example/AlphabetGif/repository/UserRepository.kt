package com.example.AlphabetGif.repository

import com.example.AlphabetGif.model.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, Long> {
    fun existsByUserName(login: String): Boolean
    fun findByUserName(login: String): Optional<User>
}