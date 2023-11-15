package com.proton.money.chat.com.proton.money.chat.messengerresources

import com.proton.money.chat.models.SendMessageHistoryRequest
import com.proton.money.chat.models.SendMessageRequest
import com.proton.money.chat.response.SuccessResponseWithMessage
import com.proton.money.chat.response.SuccessResponseWithoutMessage
import com.proton.money.chat.service.MessagingService
import lombok.AllArgsConstructor
import org.springframework.data.repository.query.Param
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
@AllArgsConstructor
class MessagingResource(private val messagingService: MessagingService) {

    @GetMapping("/get/unread/{userName}")
    fun getUnreadMessageForUser(@PathVariable userName: String): Any {
        return messagingService.getAllUnreadMessagesForTheUser(userName = userName)
    }
    @GetMapping("/get/history")
    fun getAllUser(@RequestBody sendMessageHistoryRequest: SendMessageHistoryRequest): Any {
        return messagingService.getAllMessageForUser(friend = sendMessageHistoryRequest.friend!!, user = sendMessageHistoryRequest.user!!)
    }
    @PostMapping("/send/text/user")
    fun sendTextMessage(@RequestBody sendMessageRequest: SendMessageRequest): Any {
        return messagingService.saveMessage(sendMessageRequest)
    }


}