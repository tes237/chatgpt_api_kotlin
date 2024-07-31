package com.ai.kottest001.vo

import com.google.gson.annotations.SerializedName

data class CommonChattingMessagesVO (
    @SerializedName("model")var model:String, //gpt-3.5-turbo-0125
    @SerializedName("messages")var messages:List<MessageVO>,
)