package com.ai.kottest001.vo.receive

import com.ai.kottest001.vo.MessageVO
import com.google.gson.annotations.SerializedName

data class ChoiceVO (
    @SerializedName("index")val index:Int,
    @SerializedName("message")val message: MessageVO, //"You are a helpful and kind AI Assistant."
    @SerializedName("logprobs")val logprobs:String?,
    @SerializedName("finish_reason")val finish_reason:String,
)