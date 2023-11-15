package com.proton.money.chat.repo

import com.proton.money.chat.entities.LoggedInUser
import lombok.AllArgsConstructor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Logged in user repository
 *
 * This repo is used to do all crud action on LoggedInUsers table
 * @constructor Create empty Logged in user repository
 */
@Repository
@AllArgsConstructor
interface LoggedInUserRepository : JpaRepository<LoggedInUser, Int> {

    /**
     * Find users by user name
     *
     * @param userName
     * @return
     */
    fun findUsersByUserName(userName: String): LoggedInUser?

    /**
     * Delete by user name
     *
     * @param userName
     */
    fun deleteByUserName(userName: String)
}