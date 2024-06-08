package com.example.AlphabetGif.model

import jakarta.persistence.*

@Entity
@Table(name = "t_users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "user_name")
    val userName: String,

    @Column(name = "user_phone")
    val userPhone: String? = null,

    @Column(name = "password")
    val password: String? = null
)