package com.ai.kottest001.networks

import com.ai.kottest001.vo.receive.ChoiceVO

interface MessagesInterface {

    public fun onSuccess(result : List<ChoiceVO>?)

    public fun onError(error : Int, throwable: Throwable?)
}
