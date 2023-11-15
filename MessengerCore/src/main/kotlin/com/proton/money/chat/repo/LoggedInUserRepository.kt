package com.proton.money.chat.repo

import com.proton.money.chat.entities.LoggedInUser
import com.proton.money.chat.entities.Users
import lombok.AllArgsConstructor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.jws.soap.SOAPBinding.Use
import javax.transaction.Transactional

@Repository
@AllArgsConstructor
interface  LoggedInUserRepository : JpaRepository<LoggedInUser, Int>{

    fun findUsersByUserName(userName: String): LoggedInUser?

    fun deleteByUserName(userName: String)
}