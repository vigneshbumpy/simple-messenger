package com.proton.money.chat.repo

import com.proton.money.chat.entities.LoggedInUser
import com.proton.money.chat.entities.Messages
import com.proton.money.chat.entities.Users
import lombok.AllArgsConstructor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.jws.soap.SOAPBinding.Use
import javax.transaction.Transactional

/**
 * Message repository
 *
 * This repo is used to do all crud action on Messages table
 * @constructor Create empty Message repository
 */
@Repository
@AllArgsConstructor
interface  MessageRepository : JpaRepository<Messages, Int>{

    /**
     * Get by to user name and is read
     *
     * @param toUserName
     * @param isRead
     * @return
     */
    fun getByToUserNameAndIsRead(toUserName: String, isRead: Boolean): List<Messages>?

    /**
     * Get by from user name and to user name
     *
     * @param fromUserName
     * @param toUserName
     * @return
     */
    fun getByFromUserNameAndToUserName(fromUserName: String, toUserName: String): List<Messages>?
}