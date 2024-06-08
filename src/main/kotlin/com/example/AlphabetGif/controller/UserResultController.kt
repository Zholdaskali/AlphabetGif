package com.example.AlphabetGif.controller

import com.example.AlphabetGif.model.Result
import com.example.AlphabetGif.model.request.UserResultRequest
import com.example.AlphabetGif.repository.UserResultRepository
import com.example.AlphabetGif.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/userResult")
@CrossOrigin(origins = ["*"])
class UserResultController(
    private val userResultRepository: UserResultRepository,
    private val userService: UserService
) {
    @GetMapping("/user/{userId}")
    fun getAllResultsByUserId(@PathVariable userId: Long): ResponseEntity<List<Result>> {
        val results = userResultRepository.findByUserId(userId)
        return if (results.isNotEmpty()) {
            ResponseEntity.ok(results)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping("/add")
    fun createResult(@RequestBody request: UserResultRequest): ResponseEntity<Result> {
        return try {
            val user = userService.getById(request.userId)
            val result = Result(
                userResult = request.userResult,
                user = user
            )

            val savedResult = userResultRepository.save(result)
            ResponseEntity(savedResult, HttpStatus.CREATED)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @DeleteMapping("/delete/{userResultId}")
    fun delete(@PathVariable userResultId: Long): ResponseEntity<String> {
        return if (userResultRepository.existsById(userResultId)) {
            userResultRepository.deleteById(userResultId)
            ResponseEntity("User deleted successfully", HttpStatus.OK)
        } else {
            ResponseEntity("User not found", HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/max-result/{userId}")
    fun getTopResultByUserId(@PathVariable userId: Long): ResponseEntity<Result> {
        val topResult = userResultRepository.findTopByUserIdOrderByUserResultDesc(userId)
        return if (topResult != null) {
            ResponseEntity.ok(topResult)
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
