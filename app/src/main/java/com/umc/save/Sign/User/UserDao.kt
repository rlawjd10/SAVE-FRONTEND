package com.umc.save.Sign.User

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    //user에 대한 정보를 넣기 위함
    @Insert
    fun insert(user: User)

    //userTable에 저장될 모든 정보를 가져올 수 있도록 하는 Query문
    @Query("SELECT * FROM UserTable")
    fun getUsers(): List<User>

    //한 명의 유저에 대한 정보를 가져올 수 있는 쿼리문
    //이메일과 패스워드가 똑같은 유저의 모든 정보를 가져오라는 의미
    @Query("SELECT * FROM UserTable WHERE email = :email AND password = :password")
    fun getUser(email: String, password: String): User?
}