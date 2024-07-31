package com.ai.kottest001.networks

import com.ai.kottest001.vo.receive.CollectionResponseVO
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface OpenAIService {

    @POST("/v1/chat/completions")
    fun reqChatmessage(@Body requestBody: RequestBody): Call<CollectionResponseVO> //반환하는 값SearchBookDto

}