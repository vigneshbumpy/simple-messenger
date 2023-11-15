package com.proton.money.chat.service

import com.proton.money.chat.entities.Messages
import com.proton.money.chat.models.SendMessageRequest
import com.proton.money.chat.models.UnreadMessageResponse
import com.proton.money.chat.repo.MessageRepository
import com.proton.money.chat.response.SuccessResponse
import com.proton.money.chat.response.SuccessResponseWithMessage
import lombok.AllArgsConstructor
import org.springframework.stereotype.Service

@Service
@AllArgsConstructor
class MessagingService(
    private val messageRepository: MessageRepository
) {
    fun getAllUnreadMessagesForTheUser(userName:String): Any {
        val listOfUnreadMessages = messageRepository.getByToUserNameAndIsRead(toUserName = userName, isRead = false)
        if (listOfUnreadMessages.isNullOrEmpty()) {
            return SuccessResponseWithMessage(message =  "No new messages", data = null)
        }
        val listOfUnreadMessageResponse = listOfUnreadMessages.groupBy { it.fromUserName }

        val listOfResponse = mutableListOf<UnreadMessageResponse>()
        listOfUnreadMessageResponse.forEach {
            listOfResponse.add(UnreadMessageResponse (
                userName = it.key!!,
                texts = it.value.map { it.text }.toList()
            ))
        }
        return SuccessResponseWithMessage(message = "You have message(s)", data = listOfResponse)
    }
    fun getAllMessageForUser(friend:String, user: String): Any {
        val allMessages = mutableListOf<Messages>()
        allMessages.addAll(messageRepository.getByFromUserNameAndToUserName(fromUserName = friend, toUserName = user) ?: emptyList())
        allMessages.addAll(messageRepository.getByFromUserNameAndToUserName(fromUserName = user, toUserName = friend) ?: emptyList())


        if (allMessages.isEmpty()) {
            return SuccessResponseWithMessage(message =  "No new messages", data = null)
        }
        val listOfUnreadMessageResponse = allMessages.groupBy { it.fromUserName }

        val listOfResponse = mutableListOf<UnreadMessageResponse>()
        listOfUnreadMessageResponse.forEach {
            listOfResponse.add(UnreadMessageResponse (
                userName = it.key!!,
                texts = it.value.map { it.text }.toList()
            ))
        }
        return SuccessResponseWithMessage(message = "You have message(s)", data = listOfResponse)
    }
    fun saveMessage(sendMessageRequest: SendMessageRequest): Any {
        messageRepository.save(
            Messages (
                fromUserName = sendMessageRequest.fromUserName,
                toUserName = sendMessageRequest.toUserName,
                text = sendMessageRequest.text,
                isRead = false
            )
        )
        return SuccessResponse()
    }
}