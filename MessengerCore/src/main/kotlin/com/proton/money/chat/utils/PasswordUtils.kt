package com.proton.money.chat.utils

import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.Cipher
import javax.crypto.SecretKey


/**
 * Password utils
 *
 * This is used for all password related utils
 *
 * @constructor Create Password utils
 */
@Component
class PasswordUtils {
    /**
     * Encode
     *
     * @param plainText
     * @return
     */
    @Throws(Exception::class)
    fun encode(plainText: String): String? {
        val plainTextByte = plainText.toByteArray()
        val encoder: Base64.Encoder = Base64.getEncoder()
        return encoder.encodeToString(plainTextByte)
    }

    /**
     * Decode
     *
     * @param encryptedText
     * @return
     */
    @Throws(Exception::class)
    fun decode(encryptedText: String?): String? {
        val decoder: Base64.Decoder = Base64.getDecoder()
        val encryptedTextByte: ByteArray = decoder.decode(encryptedText)
        return String(encryptedTextByte)
    }
}