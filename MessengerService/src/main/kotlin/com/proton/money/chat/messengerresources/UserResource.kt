package com.proton.money.chat.messengerresources

import com.proton.money.chat.models.ResponseObject
import com.proton.money.chat.models.UserRequest
import com.proton.money.chat.service.UserService
import lombok.AllArgsConstructor
import org.springframework.web.bind.annotation.*

/**
 * User resource
 *
 * This resource takes care of all the user related request and redirecting to the corresponding services
 *
 * @property userService
 * @constructor Create empty User resource
 */
@RestController
@AllArgsConstructor
class UserResource(private val userService: UserService) {

    /**
     * Get all user
     *
     * @return
     */
    @GetMapping("/get/users")
    fun getAllUser(): ResponseObject {
        return userService.getAllUsers()
    }

    /**
     * User login
     *
     * @param userRequest
     * @return
     */
    @PostMapping("/login", consumes = arrayOf("application/json"), produces = arrayOf("application/json"))
    @ResponseBody
    fun userLogin(@RequestBody userRequest: UserRequest): ResponseObject {

        if (userRequest.userName.isEmpty()) {
            throw IllegalArgumentException("Username cannot be Empty")
        }

        if (userRequest.passcode.isEmpty()) {
            throw IllegalArgumentException("Passcode cannot be Empty")
        }
        return userService.logInUser(userRequest.userName, userRequest.passcode)
    }

    /**
     * Logout user
     *
     * @param userRequest
     * @return
     */
    @PostMapping("/logout/{username}", consumes = arrayOf("application/json"), produces = arrayOf("application/json"))
    @ResponseBody
    fun logoutUser(@PathVariable userName: String): ResponseObject {
        if (userName.isEmpty()) {
            throw IllegalArgumentException("Username cannot be Empty")
        }
        return userService.logoutUser(userName)
    }

    /**
     * Create user
     *
     * @param userRequest
     * @return
     */
    @PostMapping("/create/user", consumes = arrayOf("application/json"), produces = arrayOf("application/json"))
    @ResponseBody
    fun createUser(@RequestBody userRequest: UserRequest): ResponseObject {
        if (userRequest.userName.isEmpty()) {
            throw IllegalArgumentException("Username cannot be Empty")
        }

        if (userRequest.passcode.isEmpty()) {
            throw IllegalArgumentException("Passcode cannot be Empty")
        }
        return userService.createUser(userRequest.userName, userRequest.passcode)
    }
}