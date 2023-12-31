package com.proton.money.chat

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

/**
 * Messenger application
 *
 * This is the main spring boot application
 *
 * @constructor Create Messenger application
 */
@EntityScan("com.proton.money.chat.entities")
@EnableJpaRepositories("com.proton.money.chat.repo")
@EnableAspectJAutoProxy
@EnableConfigurationProperties
@SpringBootApplication
open class MessengerApplication

/**
 * Main
 *
 * @param args
 */
fun main(args: Array<String>) {
    SpringApplication.run(MessengerApplication::class.java, *args)
}