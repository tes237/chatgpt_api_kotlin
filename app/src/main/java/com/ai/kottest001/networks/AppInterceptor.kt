package com.ai.kottest001.networks

import com.ai.kottest001.activity.main.MainActivity
import com.ai.kottest001.helper.SharedPreferenceHelper
import okhttp3.Interceptor
import javax.inject.Inject

class AppInterceptor : Interceptor {
    //@Throws(IOException::class)

    lateinit var openAIkey : String

    constructor(tmpKey : String)
    {
        openAIkey = tmpKey
    }

    override fun intercept(chain: Interceptor.Chain) = with(chain)
    {
        val newRequest = request().newBuilder()
            .addHeader("Authorization", "Bearer " + openAIkey)
            .build()
        proceed(newRequest)
    }
}