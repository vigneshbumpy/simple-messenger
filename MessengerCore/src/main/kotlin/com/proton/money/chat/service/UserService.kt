package com.proton.money.chat.service

import com.proton.money.chat.entities.LoggedInUser
import com.proton.money.chat.entities.Users
import com.proton.money.chat.models.ResponseObject
import com.proton.money.chat.repo.LoggedInUserRepository
import com.proton.money.chat.repo.UserRepository
import com.proton.money.chat.utils.PasswordUtils
import lombok.AllArgsConstructor
import org.springframework.stereotype.Service

/**
 * User service
 *
 * This service takes care of the user life cycle management
 *
 * @property userRepository
 * @property loggedInUserRepository
 * @property passwordUtils
 * @constructor Create empty User service
 */
@Service
@AllArgsConstructor
class UserService(
    private val userRepository: UserRepository,
    private val loggedInUserRepository: LoggedInUserRepository,
    private val passwordUtils: PasswordUtils
) {

    /**
     * Log in user
     *
     * @param userName
     * @param password
     * @return
     */
    fun logInUser(userName: String, password: String): ResponseObject {
        val userData = userRepository.findUsersByUserName(userName)
        if (userData == null) {
            return ResponseObject(status = "failure", message = "User does not exists")
        }
        if (loggedInUserRepository.findUsersByUserName(userName) != null) {
            return ResponseObject(status = "failure", message = "User already logged in")
        }
        if (password != passwordUtils.decode(userData.password)) {
            return ResponseObject(status = "failure", message = "UserId and Password does not match")
        }
        loggedInUserRepository.save(
            LoggedInUser(
                userName = userName
            )
        )
        return ResponseObject()
    }

    /**
     * Create user
     *
     * @param userName
     * @param password
     * @return
     */
    fun createUser(userName: String, password: String): ResponseObject {

        if (userRepository.findUsersByUserName(userName) != null) {
            return ResponseObject(status = "failure", message = "User already exists")
        }

        val encodedPassword = passwordUtils.encode(password)

        userRepository.save(
            Users(
                userName = userName,
                password = encodedPassword
            )
        )
        return ResponseObject()
    }

    /**
     * Get all users
     *
     * @return
     */
    fun getAllUsers(): ResponseObject {
        return ResponseObject(data = userRepository.findAllUsers())
    }

    /**
     * Logout user
     *
     * @param userName
     * @return
     */
    fun logoutUser(userName: String): ResponseObject {
        if (userRepository.findUsersByUserName(userName) == null) {
            return ResponseObject(status = "failure", message = "User does not exists")
        }
        if (loggedInUserRepository.findUsersByUserName(userName) == null) {
            return ResponseObject(status = "failure", message = "User Not Logged in")
        }

        loggedInUserRepository.deleteByUserName(userName)
        return ResponseObject()
    }

    fun blockUser(userName: String, blockingUserName: String): ResponseObject {
        val currentUserState = userRepository.findUsersByUserName(userName)
        val blockedUserUpdated = if (currentUserState?.blockedUser != null) {
            currentUserState.blockedUser + ",$blockingUserName"
        } else {
            blockingUserName
        }
        currentUserState?.blockedUser = blockedUserUpdated
        if (currentUserState != null) {
            userRepository.save(currentUserState)
        }
        return ResponseObject()
    }
}