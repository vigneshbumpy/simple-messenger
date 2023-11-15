package com.proton.money.chat.models

data class UserRequest(
    val userName: String,
    val passcode:String
)

data class UnreadMessageResponse (
    val userName: String?,
    val texts: List<String?>
)

data class SendMessageRequest (
    val fromUserName: String?,
    val toUserName: String?,
    val text: String
)

data class SendMessageHistoryRequest (
    val friend: String?,
    val user: String?
)