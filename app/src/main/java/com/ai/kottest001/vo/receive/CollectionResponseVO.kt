package com.ai.kottest001.vo.receive

import com.google.gson.annotations.SerializedName

data class CollectionResponseVO (
    @SerializedName("id")val id:String,
    @SerializedName("object")val objectData:String,
    @SerializedName("created")val created:String,
    @SerializedName("model")val model:String,
    @SerializedName("system_fingerprint")val system_fingerprint:String?,

    @SerializedName("choices")val choices:List<ChoiceVO>,
)