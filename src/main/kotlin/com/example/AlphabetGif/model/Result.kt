package com.example.AlphabetGif.model

import jakarta.persistence.*
@Entity
@Table(name = "t_user_result")
data class Result (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "user_result")
    val userResult: Int,

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    var user: User
)