package com.example.AlphabetGif.repository

import org.springframework.data.jpa.repository.JpaRepository
import com.example.AlphabetGif.model.Result
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.*
import java.util.*

interface UserResultRepository : JpaRepository <Result, Long> {
    fun findTopByUserIdOrderByUserResultDesc(userId: Long): Result?
    fun findByUserId(userId: Long): List<Result>
}