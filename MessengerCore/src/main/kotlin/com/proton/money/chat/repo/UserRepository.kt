package com.proton.money.chat.repo

import com.proton.money.chat.entities.Users
import lombok.AllArgsConstructor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository


/**
 * User repository
 *
 * This repo is used to do all crud action on Users table
 * @constructor Create empty User repository
 */
@Repository
@AllArgsConstructor
interface UserRepository : JpaRepository<Users, Int> {

    /**
     * Find users by user name
     *
     * @param userName
     * @return
     */
    fun findUsersByUserName(userName: String): Users?

    /**
     * Find all users
     *
     * @return
     */
    @Query("SELECT distinct userName FROM Users")
    fun findAllUsers(): List<String>
}