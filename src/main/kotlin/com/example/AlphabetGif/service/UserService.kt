package com.example.AlphabetGif.service

import com.example.AlphabetGif.exception.UserNotFoundException
import com.example.AlphabetGif.model.User
import com.example.AlphabetGif.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun getById(userId: Long): User {
        return userRepository.findById(userId).orElseThrow { UserNotFoundException() }
    }
}