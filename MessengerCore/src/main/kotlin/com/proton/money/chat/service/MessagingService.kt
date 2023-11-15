package com.proton.money.chat.service

import com.proton.money.chat.entities.Messages
import com.proton.money.chat.models.ResponseObject
import com.proton.money.chat.models.SendMessageRequest
import com.proton.money.chat.models.UnreadMessageResponse
import com.proton.money.chat.repo.LoggedInUserRepository
import com.proton.money.chat.repo.MessageRepository
import com.proton.money.chat.repo.UserRepository
import lombok.AllArgsConstructor
import org.springframework.stereotype.Service

/**
 * Messaging service
 *
 * This services takes care of all the request/response lifecycle of all the messages
 * @property messageRepository
 * @constructor Create empty Messaging service
 */
@Service
@AllArgsConstructor
class MessagingService(
    private val messageRepository: MessageRepository,
    private val userRepository: UserRepository,
    private val loggedInUserRepository: LoggedInUserRepository
) {
    /**
     * Get all unread messages for the user
     *
     * @param userName
     * @return
     */
    fun getAllUnreadMessagesForTheUser(userName: String): ResponseObject {
        if (userRepository.findUsersByUserName(userName) == null) {
            return ResponseObject(status = "failure", message = "User does not exists")
        }
        if (loggedInUserRepository.findUsersByUserName(userName) == null) {
            return ResponseObject(status = "failure", message = "User not logged in")
        }
        val listOfUnreadMessages = messageRepository.getByToUserNameAndIsRead(toUserName = userName, isRead = false)
        if (listOfUnreadMessages.isNullOrEmpty()) {
            return ResponseObject(message = "No new messages")
        }
        val listOfUnreadMessageResponse = listOfUnreadMessages.groupBy { it.fromUserName }

        val listOfResponse = mutableListOf<UnreadMessageResponse>()
        listOfUnreadMessageResponse.forEach {
            listOfResponse.add(
                UnreadMessageResponse(
                    userName = it.key!!,
                    texts = it.value.map { it.text }.toList()
                )
            )
        }
        return ResponseObject(message = "You have message(s)", data = listOfResponse)
    }

    /**
     * Get all message for user
     *
     * @param friend
     * @param user
     * @return
     */
    fun getAllMessageForUser(friend: String, user: String): ResponseObject {
        if (userRepository.findUsersByUserName(friend) == null) {
            return ResponseObject(status = "failure", message = "Friend does not exists")
        }
        if (userRepository.findUsersByUserName(user) == null) {
            return ResponseObject(status = "failure", message = "User are not registered")
        }
        if (loggedInUserRepository.findUsersByUserName(user) == null) {
            return ResponseObject(status = "failure", message = "User not logged in")
        }
        val allMessages = mutableListOf<Messages>()
        allMessages.addAll(
            messageRepository.getByFromUserNameAndToUserName(fromUserName = friend, toUserName = user) ?: emptyList()
        )
        allMessages.addAll(
            messageRepository.getByFromUserNameAndToUserName(fromUserName = user, toUserName = friend) ?: emptyList()
        )


        if (allMessages.isEmpty()) {
            return ResponseObject(message = "No new messages")
        }
        val listOfUnreadMessageResponse = allMessages.groupBy { it.fromUserName }

        val listOfResponse = mutableListOf<UnreadMessageResponse>()
        listOfUnreadMessageResponse.forEach {
            listOfResponse.add(
                UnreadMessageResponse(
                    userName = it.key!!,
                    texts = it.value.map { it.text }.toList()
                )
            )
        }
        return ResponseObject(data = listOfResponse)
    }

    /**
     * Save message
     *
     * @param sendMessageRequest
     * @return
     */
    fun saveMessage(sendMessageRequest: SendMessageRequest): ResponseObject {
        if (userRepository.findUsersByUserName(sendMessageRequest.fromUserName) == null) {
            return ResponseObject(status = "failure", message = "User does not exists")
        }
        if (userRepository.findUsersByUserName(sendMessageRequest.toUserName) == null) {
            return ResponseObject(status = "failure", message = "Friend does not exists")
        }
        if (loggedInUserRepository.findUsersByUserName(sendMessageRequest.fromUserName) == null) {
            return ResponseObject(status = "failure", message = "User not logged in")
        }
        messageRepository.save(
            Messages(
                fromUserName = sendMessageRequest.fromUserName,
                toUserName = sendMessageRequest.toUserName,
                text = sendMessageRequest.text,
                isRead = false
            )
        )
        return ResponseObject()
    }
}