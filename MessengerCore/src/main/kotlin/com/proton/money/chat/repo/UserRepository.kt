package com.proton.money.chat.repo

import com.proton.money.chat.entities.Users
import lombok.AllArgsConstructor
import org.springframework.boot.autoconfigure.security.SecurityProperties.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository


@Repository
@AllArgsConstructor
interface  UserRepository : JpaRepository<Users, Int>{

    fun findUsersByUserName(userName: String): Users?

    @Query("SELECT distinct userName FROM Users")
    fun findAllUsers(): List<String>
}