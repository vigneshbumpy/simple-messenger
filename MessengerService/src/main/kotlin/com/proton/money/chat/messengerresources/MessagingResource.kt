package com.proton.money.chat.com.proton.money.chat.messengerresources

import com.proton.money.chat.models.ResponseObject
import com.proton.money.chat.models.SendMessageHistoryRequest
import com.proton.money.chat.models.SendMessageRequest
import com.proton.money.chat.service.MessagingService
import lombok.AllArgsConstructor
import org.springframework.web.bind.annotation.*

/**
 * Messaging resource
 *
 * This resource takes care of all the messaging related request and redirecting to the corresponding services
 *
 * @property messagingService
 * @constructor Create empty Messaging resource
 */
@RestController
@AllArgsConstructor
class MessagingResource(private val messagingService: MessagingService) {

    /**
     * Get unread message for user
     *
     * @param userName
     * @return
     */
    @GetMapping("/get/unread/{userName}")
    fun getUnreadMessageForUser(@PathVariable userName: String): ResponseObject {
        if (userName.isEmpty()) {
            throw IllegalArgumentException("Username cannot be Empty")
        }
        return messagingService.getAllUnreadMessagesForTheUser(userName = userName)
    }

    /**
     * Get all user
     *
     * @param sendMessageHistoryRequest
     * @return
     */
    @GetMapping("/get/history")
    fun getAllUser(@RequestBody sendMessageHistoryRequest: SendMessageHistoryRequest): ResponseObject {
        if (sendMessageHistoryRequest.friend.isEmpty()) {
            throw IllegalArgumentException("Friend name cannot be Empty")
        }
        if (sendMessageHistoryRequest.user.isEmpty()) {
            throw IllegalArgumentException("User name cannot be Empty")
        }
        return messagingService.getAllMessageForUser(
            friend = sendMessageHistoryRequest.friend,
            user = sendMessageHistoryRequest.user
        )
    }

    /**
     * Send text message
     *
     * @param sendMessageRequest
     * @return
     */
    @PostMapping("/send/text/user")
    fun sendTextMessage(@RequestBody sendMessageRequest: SendMessageRequest): ResponseObject {
        if (sendMessageRequest.fromUserName.isEmpty()) {
            throw IllegalArgumentException("Message need to have from user name cannot be Empty")
        }
        if (sendMessageRequest.toUserName.isEmpty()) {
            throw IllegalArgumentException("Message need to have to user name cannot be Empty")
        }
        if (sendMessageRequest.text.isEmpty()) {
            throw IllegalArgumentException("Message cannot be Empty")
        }
        return messagingService.saveMessage(sendMessageRequest)
    }


}