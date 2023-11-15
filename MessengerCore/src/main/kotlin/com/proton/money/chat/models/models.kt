package com.proton.money.chat.models

/**
 * User request
 *
 * @property userName
 * @property passcode
 * @constructor Create empty User request
 */
data class UserRequest(
    val userName: String,
    val passcode:String
)

/**
 * Unread message response
 *
 * @property userName
 * @property texts
 * @constructor Create empty Unread message response
 */
data class UnreadMessageResponse (
    val userName: String?,
    val texts: List<String?>
)

/**
 * Send message request
 *
 * @property fromUserName
 * @property toUserName
 * @property text
 * @constructor Create empty Send message request
 */
data class SendMessageRequest (
    val fromUserName: String?,
    val toUserName: String?,
    val text: String
)

/**
 * Send message history request
 *
 * @property friend
 * @property user
 * @constructor Create empty Send message history request
 */
data class SendMessageHistoryRequest (
    val friend: String?,
    val user: String?
)