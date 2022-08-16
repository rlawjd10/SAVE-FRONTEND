package com.umc.save.Sign.User

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

//회원가입 Body
@Entity(tableName = "UserTable")
data class User(
    @SerializedName(value = "isSnsAuth") val int: Int,
    @SerializedName(value = "name") val name: String,
    @SerializedName(value = "phone") val phone: String,
    @SerializedName(value = "email") val email: String,
    @SerializedName(value = "password") val password: String
) {
    //자동적으로 사용자가 추가될 때마다 count
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}

