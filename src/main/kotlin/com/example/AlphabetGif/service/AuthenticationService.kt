package com.example.AlphabetGif.service

import com.example.AlphabetGif.encoder.BCryptPasswordEncoder
import com.example.AlphabetGif.exception.UserNotFoundException
import com.example.AlphabetGif.exception.UserAlreadyExistsException
import com.example.AlphabetGif.exception.WrongCredentialsException
import com.example.AlphabetGif.model.User
import com.example.AlphabetGif.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class AuthenticationService(private val userRepository: UserRepository) {

    private val passwordEncoder = BCryptPasswordEncoder(10)

    fun register(userName: String, password: String, userPhone: String): Long? {
        if (userRepository.existsByUserName(userName)) {
            throw UserAlreadyExistsException()
        }

        val encodedPassword = passwordEncoder.hash(password)
        val customer = User(password = encodedPassword, userName = userName, userPhone = userPhone)
        userRepository.save(customer)

        return customer.id
    }

    fun login(userName: String, password: String): Long? {
        val customer = userRepository.findByUserName(userName)
            .orElseThrow { UserNotFoundException() }

        val passwordMatches = customer.password?.let {
            passwordEncoder.check(password, it)
        } ?: false

        if (!passwordMatches) {
            throw WrongCredentialsException()
        }

        return customer.id
    }
}