package com.proton.money.chat.utils

import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.Cipher
import javax.crypto.SecretKey


@Component
class PasswordUtils {
    @Throws(Exception::class)
    fun encode(plainText: String): String? {
        val plainTextByte = plainText.toByteArray()
        val encoder: Base64.Encoder = Base64.getEncoder()
        return encoder.encodeToString(plainTextByte)
    }

    @Throws(Exception::class)
    fun decode(encryptedText: String?): String? {
        val decoder: Base64.Decoder = Base64.getDecoder()
        val encryptedTextByte: ByteArray = decoder.decode(encryptedText)
        return String(encryptedTextByte)
    }
}