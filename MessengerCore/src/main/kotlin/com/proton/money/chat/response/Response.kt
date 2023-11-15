package com.proton.money.chat.response

import sun.plugin2.message.Message
import kotlin.system.measureNanoTime

open class Response(val status: String) {
}

data class SuccessResponse(
    val status: String = "success"
)

data class FailureResponse(
    val status: String = "failure",
    val message: String =""
)

data class SuccessResponseWithoutMessage(
    val status: String = "success",
    val data: Any
)

data class SuccessResponseWithMessage(
    val status: String = "success",
    val message: String ="",
    val data: Any?
)