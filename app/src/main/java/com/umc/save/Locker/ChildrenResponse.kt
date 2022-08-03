package com.umc.save.Locker

import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList

data class ChildrenResponse (
    @SerializedName(value = "isSuccess") val isSuccess : Boolean,
    @SerializedName(value = "code") val code : Int,
    @SerializedName(value = "message") val message : String,
    @SerializedName(value = "result") val result : ArrayList<Child>?
)

//data class Children(
//    @SerializedName(value = "childIdx") val childIdx : Int,
//    @SerializedName(value = "name") val name : String,
//    @SerializedName(value = "gender") val gender : String,
//    @SerializedName(value = "age") val age : String,
//    @SerializedName(value = "address") val address : String
//)


//data class Child(
//    @SerializedName(value = "childIdx") var childIdx: Int,
//    @SerializedName(value = "name")var childName: String,
//    var isCertain: Boolean,
//    @SerializedName(value = "gender") var childGender: String,
//    @SerializedName(value = "age") var childAge: String,
//    @SerializedName(value = "address") var childAddress: String,
//    @SerializedName(value = "detailAddress") var childDetailAddress: String? = "",
//    var createAt: Date
//)