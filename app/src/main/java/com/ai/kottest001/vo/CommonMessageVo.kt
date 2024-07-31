package com.ai.kottest001.vo

import com.google.gson.annotations.SerializedName

data class MessageVO (
    @SerializedName("role")var role:String, //"user"
    @SerializedName("content")var msg:String, //"You are a helpful and kind AI Assistant."
)