package com.proton.money.chat.messengerresources

import com.proton.money.chat.models.UserRequest
import com.proton.money.chat.response.SuccessResponseWithoutMessage
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
    @GetMapping("/getAll")
    fun getAllUser(): SuccessResponseWithoutMessage {
        return userService.getAllUsers()
    }

    /**
     * Get password
     *
     * @param userName
     * @return
     */
    @GetMapping("/getPassword/{userName}")
    fun getPassword(@PathVariable userName: String): String {
        return userService.getPassword(userName)?: "Username not available"
    }

    /**
     * User login
     *
     * @param userRequest
     * @return
     */
    @PostMapping( "/login", consumes = arrayOf("application/json"), produces = arrayOf("application/json"))
    @ResponseBody
    fun userLogin(@RequestBody userRequest: UserRequest): Any {
        return userService.logInUser(userRequest.userName, userRequest.passcode)
    }

    /**
     * Logout user
     *
     * @param userRequest
     * @return
     */
    @PostMapping( "/logout", consumes = arrayOf("application/json"), produces = arrayOf("application/json"))
    @ResponseBody
    fun logoutUser(@RequestBody userRequest: UserRequest): Any {
        return userService.logoutUser(userRequest.userName)
    }

    /**
     * Create user
     *
     * @param userRequest
     * @return
     */
    @PostMapping( "/create/user", consumes = arrayOf("application/json"), produces = arrayOf("application/json"))
    @ResponseBody
    fun createUser(@RequestBody userRequest: UserRequest): Any {
        return userService.createUser(userRequest.userName, userRequest.passcode)
    }
}