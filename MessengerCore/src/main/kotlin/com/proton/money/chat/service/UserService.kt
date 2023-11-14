package com.proton.money.chat.service

import com.proton.money.chat.entities.Users
import com.proton.money.chat.repo.UserRepository
import com.proton.money.chat.utils.PasswordUtils
import lombok.AllArgsConstructor
import org.springframework.stereotype.Service

@Service
@AllArgsConstructor
class UserService(private val userRepository: UserRepository,
                  private val passwordUtils: PasswordUtils
){
    fun createUser(userName: String, password: String) {
        val encodedPassword = passwordUtils.encode(password)

        userRepository.save(
            Users (
                userName = userName,
                password = encodedPassword
            )
        )
    }

    fun getPassword(userName: String): String? {
        return passwordUtils.decode(userRepository.findUsersByUserName(userName).password)
    }
    fun getAll(): Long {
        return userRepository.count()
    }
}