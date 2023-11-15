package com.proton.money.chat.service

import com.proton.money.chat.entities.LoggedInUser
import com.proton.money.chat.entities.Users
import com.proton.money.chat.repo.LoggedInUserRepository
import com.proton.money.chat.repo.UserRepository
import com.proton.money.chat.response.FailureResponse
import com.proton.money.chat.response.Response
import com.proton.money.chat.response.SuccessResponse
import com.proton.money.chat.response.SuccessResponseWithoutMessage
import com.proton.money.chat.utils.PasswordUtils
import lombok.AllArgsConstructor
import org.springframework.stereotype.Service

@Service
@AllArgsConstructor
class UserService(
    private val userRepository: UserRepository,
    private val loggedInUserRepository: LoggedInUserRepository,
    private val passwordUtils: PasswordUtils
){

    fun logInUser(userName: String, password: String): Any {
        val userData = userRepository.findUsersByUserName(userName)
        val userLoggedInData = loggedInUserRepository.findUsersByUserName(userName)
        if (userData == null) {
            return FailureResponse(message = "User does not exists")
        }
        if (userLoggedInData != null) {
            return FailureResponse(message = "User already logged in")
        }
        if (password != passwordUtils.decode(userData.password)) {
            return FailureResponse(message = "UserId and Password does not match")
        }
        loggedInUserRepository.save(
            LoggedInUser (
                userName = userName
            )
        )
        return SuccessResponse()
    }
    fun createUser(userName: String, password: String): Any {

        if(userRepository.findUsersByUserName(userName) != null) {
            return FailureResponse(message = "User already exists")
        }

        val encodedPassword = passwordUtils.encode(password)

        userRepository.save(
            Users (
                userName = userName,
                password = encodedPassword
            )
        )
        return SuccessResponse()
    }

    fun getPassword(userName: String): String? {
        return passwordUtils.decode(userRepository.findUsersByUserName(userName)?.password)
    }
    fun getAllUsers(): SuccessResponseWithoutMessage {
        return SuccessResponseWithoutMessage(data = userRepository.findAllUsers())
    }

    fun logoutUser(userName: String): Any {
        val userData = userRepository.findUsersByUserName(userName)
        val userLoggedInData = loggedInUserRepository.findUsersByUserName(userName)
        if (userData == null) {
            return FailureResponse(message = "User does not exists")
        }
        if (userLoggedInData == null) {
            return FailureResponse(message = "User Not Logged in")
        }

        loggedInUserRepository.deleteByUserName(userName)
        return SuccessResponse()
    }
}