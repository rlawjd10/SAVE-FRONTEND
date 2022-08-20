package com.umc.save.Sign.User

import com.umc.save.Home.option.*
import com.umc.save.Sign.Auth.AuthResponse
import retrofit2.Call
import retrofit2.http.*

//회원가입
//method, url & 어떤 method를 실행시킬 것인지 표시해주는 interface
interface UserRetrofitInterface {
    @POST("/user")
    fun signUp(@Body user: User): Call<AuthResponse>

    @GET("/user/{userIdx}")
    fun getUserInfo(@Path("userIdx") userIdx : Int) : Call<UserInfoResponse>

    @PUT("/user/{userIdx}")
    fun putUserInfo(@Path("userIdx") userIdx: Int, @Body userInfo: UserInfo) : Call<EditUserResponse>

    @PATCH("/user/status/{userIdx}")
    fun outUser(@Path("userIdx") userIdx: Int) : Call<UserOutResponse>

    @PATCH(" auth/password/{userIdx}")
    fun getPwChange(@Header("X-ACCESS-TOKEN")jwt: String, @Path("userIdx")userIdx: Int, @Body pwChange: PwChange) : Call<PwChangeResponse>
}
