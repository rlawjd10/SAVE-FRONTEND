package com.umc.save.Home.option

import android.util.Log
import com.umc.save.Sign.User.UserRetrofitInterface
import com.umc.save.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditUserService {

    private lateinit var editUserView: EditUserView

    fun setEditUserView(editUserView: EditUserView) {
        this.editUserView = editUserView
    }

    fun getEditUserView(userInfo: UserInfo, userIdx: Int) {
        val editUserService = getRetrofit().create(UserRetrofitInterface::class.java)

        editUserService.putUserInfo(userIdx, userInfo).enqueue(object : Callback<EditUserResponse> {
            override fun onResponse(
                call: Call<EditUserResponse>,
                response: Response<EditUserResponse>,
            ) {
                Log.d("EDITUSER-PUT SUCCESS",response.toString())
                val resp : EditUserResponse = response.body()!!
                when(resp.code) {
                    1000 -> editUserView.onEditUserSuccess(resp.code, resp.result)
                    2004 -> editUserView.EditNotExist(resp.code, resp.message)
                    4000 -> editUserView.onEditUserFailure(resp.code, resp.message)
                }
            }

            override fun onFailure(call: Call<EditUserResponse>, t: Throwable) {
                Log.d("EDITUSER-PUT FAILURE",t.message.toString())
            }

        })

    }
}