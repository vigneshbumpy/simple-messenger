package com.proton.money.chat.messengerresources

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/chat")
class UserResource {

    @GetMapping("/unread")
    fun getUnreadMessages(): String {
        return "Testing"
    }
}