package com.proton.money.chat

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class MessengerApplication
    fun main(args: Array<String>) {
        SpringApplication.run(MessengerApplication::class.java, *args)
    }