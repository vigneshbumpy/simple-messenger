package com.proton.money.chat.response

import sun.plugin2.message.Message
import kotlin.system.measureNanoTime

/**
 * Response
 *
 * @property status
 * @constructor Create empty Response
 */
open class Response(val status: String) {
}

/**
 * Success response
 *
 * @property status
 * @constructor Create empty Success response
 */
data class SuccessResponse(
    val status: String = "success"
)

/**
 * Failure response
 *
 * @property status
 * @property message
 * @constructor Create empty Failure response
 */
data class FailureResponse(
    val status: String = "failure",
    val message: String =""
)

/**
 * Success response without message
 *
 * @property status
 * @property data
 * @constructor Create empty Success response without message
 */
data class SuccessResponseWithoutMessage(
    val status: String = "success",
    val data: Any
)

/**
 * Success response with message
 *
 * @property status
 * @property message
 * @property data
 * @constructor Create empty Success response with message
 */
data class SuccessResponseWithMessage(
    val status: String = "success",
    val message: String ="",
    val data: Any?
)