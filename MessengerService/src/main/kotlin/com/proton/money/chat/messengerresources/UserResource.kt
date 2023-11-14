package com.proton.money.chat.messengerresources

import com.google.inject.Inject
import com.proton.money.chat.entities.Users
import com.proton.money.chat.models.UserCreationRequest
import com.proton.money.chat.service.UserService
import com.proton.money.chat.utils.PasswordUtils
import lombok.AllArgsConstructor
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@AllArgsConstructor
class UserResource(private val userService: UserService) {

    @GetMapping("/getAll")
    fun getAllUser(): Long {
        return userService.getAll()
    }
    @GetMapping("/getPassword/{userName}")
    fun getPassword(@PathVariable userName: String): String {
        return userService.getPassword(userName)?: "Username not available"
    }

    @PostMapping( "/create/user", consumes = arrayOf("application/json"), produces = arrayOf("application/json"))
    fun createUser(@RequestBody userCreationRequest: UserCreationRequest) {
        return userService.createUser(userCreationRequest.userName, userCreationRequest.passcode)
    }
}