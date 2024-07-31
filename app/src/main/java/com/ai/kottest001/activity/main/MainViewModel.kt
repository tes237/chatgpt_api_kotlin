package com.ai.kottest001.activity.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ai.kottest001.helper.NetworkManagerHelper
import com.ai.kottest001.helper.SharedPreferenceHelper
import com.ai.kottest001.networks.MessagesInterface
import com.ai.kottest001.vo.CommonChattingMessagesVO
import com.ai.kottest001.vo.MessageVO
import com.ai.kottest001.vo.receive.ChoiceVO
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class MainViewModel @Inject constructor() : ViewModel()
{
    public var messageLiveData = MutableLiveData<MessageVO>()

    @Inject
    lateinit var pref : SharedPreferenceHelper

    @Inject
    lateinit var netHelper : NetworkManagerHelper

    fun reqChatmessage(userName: String, message : String)
    {

        var messages:List<MessageVO> = listOf(MessageVO(userName, message))
        var msgInfoData : CommonChattingMessagesVO = CommonChattingMessagesVO("gpt-3.5-turbo-0125", messages)

        netHelper.reqChatmessage(msgInfoData, object: MessagesInterface
        {
            override fun onSuccess(result : List<ChoiceVO>?)
            {
                if(result?.size != 0)
                {
                    for (i in result!!.indices)
                    {
                        //logi("${result!!.get(i)}")
                        messageLiveData.value = result!!.get(i).message
                    }

                }
            }

            override fun onError(error : Int, throwable: Throwable?)
            {
            }
        })

    }
}