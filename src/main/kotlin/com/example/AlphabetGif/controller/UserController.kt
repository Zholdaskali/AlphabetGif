package com.example.AlphabetGif.controller

import com.example.AlphabetGif.exception.UserNotFoundException
import com.example.AlphabetGif.model.User
import com.example.AlphabetGif.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = ["*"])
class UserController(
    private val userService: UserService
) {
    @GetMapping("/{userId}")
    fun getUser(@PathVariable userId: Long): ResponseEntity<User> {
        return try {
            val user = userService.getById(userId)
            ResponseEntity(user, HttpStatus.OK)
        } catch (e: UserNotFoundException) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }


}