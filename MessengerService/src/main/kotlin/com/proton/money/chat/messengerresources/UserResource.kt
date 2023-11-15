package com.proton.money.chat.messengerresources

import com.proton.money.chat.models.UserRequest
import com.proton.money.chat.response.SuccessResponseWithoutMessage
import com.proton.money.chat.service.UserService
import lombok.AllArgsConstructor
import org.springframework.web.bind.annotation.*

@RestController
@AllArgsConstructor
class UserResource(private val userService: UserService) {

    @GetMapping("/getAll")
    fun getAllUser(): SuccessResponseWithoutMessage {
        return userService.getAllUsers()
    }
    @GetMapping("/getPassword/{userName}")
    fun getPassword(@PathVariable userName: String): String {
        return userService.getPassword(userName)?: "Username not available"
    }

    @PostMapping( "/login", consumes = arrayOf("application/json"), produces = arrayOf("application/json"))
    @ResponseBody
    fun userLogin(@RequestBody userRequest: UserRequest): Any {
        return userService.logInUser(userRequest.userName, userRequest.passcode)
    }

    @PostMapping( "/logout", consumes = arrayOf("application/json"), produces = arrayOf("application/json"))
    @ResponseBody
    fun logoutUser(@RequestBody userRequest: UserRequest): Any {
        return userService.logoutUser(userRequest.userName)
    }

    @PostMapping( "/create/user", consumes = arrayOf("application/json"), produces = arrayOf("application/json"))
    @ResponseBody
    fun createUser(@RequestBody userRequest: UserRequest): Any {
        return userService.createUser(userRequest.userName, userRequest.passcode)
    }
}