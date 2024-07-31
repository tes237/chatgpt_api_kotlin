package com.ai.kottest001.activity.main

import com.ai.kottest001.helper.SharedPreferenceHelper
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ai.kottest001.databinding.ActivityMainBinding
import com.ai.kottest001.helper.NetworkManagerHelper
import com.ai.kottest001.networks.MessagesInterface
import com.ai.kottest001.vo.CommonChattingMessagesVO
import com.ai.kottest001.vo.MessageVO
import com.ai.kottest001.vo.receive.ChoiceVO
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity()
{
    inline fun <reified T> T.logi(message: String) = Log.i(T::class.java.simpleName, message)

    companion object
    {
        private const val TAG = "MainActivity"
    }

    @Inject
    lateinit var pref : SharedPreferenceHelper

    @Inject
    lateinit var netHelper : NetworkManagerHelper

    private lateinit var binding: ActivityMainBinding
    private val vm by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vm.reqChatmessage("user", "안녕하세요")

        vm.messageLiveData.observe(this) { data ->

            binding.tv001.text = data.role
            binding.tv002.text = data.msg

        }
        /*
        var messages:List<MessageVO> = listOf(MessageVO("user", "안녕하세요"))
        var msgInfoData : CommonChattingMessagesVO = CommonChattingMessagesVO("gpt-3.5-turbo-0125", messages)

        netHelper.reqChatmessage(msgInfoData, object: MessagesInterface
        {
            override fun onSuccess(result : List<ChoiceVO>?)
            {
                if(result?.size != 0)
                {
                    for (i in result!!.indices)
                    {
                        logi("${result!!.get(i)}")
                    }

                    viewModel.getGithubUser(name)
                    viewModel.getGithub.observe(this) { data ->

                        binding.tv001.text = data.company
                        binding.tv002.text = data.location

                    }
                }
            }

            override fun onError(error : Int, throwable: Throwable?)
            {
            }
        })
         */

    }
}