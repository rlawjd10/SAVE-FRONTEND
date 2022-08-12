package com.umc.save.Sign.User

import com.umc.save.Sign.AuthResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

//회원가입
//method, url & 어떤 method를 실행시킬 것인지 표시해주는 interface
interface UserRetrofitInterface {
    @POST("/users")
    fun signUp(@Body user: User): Call<AuthResponse>
}
