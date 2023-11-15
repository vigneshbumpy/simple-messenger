package com.proton.money.chat.entities

import lombok.AllArgsConstructor
import javax.persistence.*

/**
 * Logged in user Table Entity
 *
 * @property id - Auto incremented id
 * @property userName - Logged in user names
 * @constructor Create empty Logged in user
 */
@Entity
@Table(name = "LoggedInUsers")
@AllArgsConstructor
class LoggedInUser(
    //mark id as primary key
    @Id //defining id as column name
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    //defining userName as column name
    @Column(nullable = false)
    val userName: String? = null
)