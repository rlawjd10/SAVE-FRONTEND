package com.umc.save.Sign.Auth

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

//AUTH
//로그인 Body
@Entity(tableName = "UserTable")
data class Auth(
    @SerializedName(value = "email")val email: String,
    @SerializedName(value = "password")val password: String
)