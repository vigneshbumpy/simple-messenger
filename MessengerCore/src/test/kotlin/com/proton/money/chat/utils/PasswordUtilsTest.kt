package com.proton.money.chat.utils

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.context.annotation.DependsOn
import java.util.*

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class PasswordUtilsTest {

    private val passwordUtils =  PasswordUtils()
    val password = "testing"
    @Test
    @Order(1)
    fun encode() {
        assertEquals(Base64.getEncoder().encodeToString(password.toByteArray()), passwordUtils.encode(password) ?: "")
    }

    @Test
    @Order(2)
    @DependsOn("encode")
    fun decode() {
        val encodedPassword = passwordUtils.encode(password) ?: ""
        assertEquals(password, passwordUtils.decode(encodedPassword))
    }
}