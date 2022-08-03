package com.umc.save.Sign.User

//회원가입 Body
data class User(
    val name: String,
    val phone: String,
    val email: String = "",
    val password: String
)
