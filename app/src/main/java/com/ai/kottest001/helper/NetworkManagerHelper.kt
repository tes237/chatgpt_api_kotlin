package com.ai.kottest001.helper

import androidx.lifecycle.ViewModel
import com.ai.kottest001.networks.AppInterceptor
import com.ai.kottest001.networks.MessagesInterface
import com.ai.kottest001.networks.OpenAIService
import com.ai.kottest001.vo.CommonChattingMessagesVO
import com.ai.kottest001.vo.receive.CollectionResponseVO
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkManagerHelper
{
    companion object
    {
        private const val CONNECT_TIMEOUT_SEC = 20000L
        private const val BASE_URL1 = "https://api.openai.com"
    }

    lateinit private var client : OkHttpClient
    lateinit private var retrofit : Retrofit
    lateinit private var openAIService : OpenAIService

    constructor(pref : SharedPreferenceHelper)
    {
        val logging_interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val appInterceptor : AppInterceptor = AppInterceptor(pref.openAIkey ?: "")

        client = OkHttpClient.Builder().apply {
            addInterceptor(logging_interceptor)
            addInterceptor(appInterceptor)
            connectTimeout(CONNECT_TIMEOUT_SEC, TimeUnit.SECONDS)
        }.build()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL1)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        openAIService = retrofit.create(OpenAIService::class.java)
    }

    fun reqChatmessage(tmpData : CommonChattingMessagesVO, tmpInterface : MessagesInterface)
    {
        val jsonObjectString = Gson().toJson(tmpData)
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        openAIService.reqChatmessage(requestBody)
            .enqueue(object: Callback<CollectionResponseVO>
            {
                override fun onFailure(call: Call<CollectionResponseVO>, t: Throwable)
                {
                    tmpInterface.onError(-1, t);
                }

                override fun onResponse(call: Call<CollectionResponseVO>, response: Response<CollectionResponseVO>)
                {
                    if(response.isSuccessful.not())
                    {
                        tmpInterface.onError(0, null);
                        return
                    }
                    val retArr = response.body()?.let {
                        it.choices
                    }
                    tmpInterface.onSuccess(retArr)
                    return
                }
            })
    }
}