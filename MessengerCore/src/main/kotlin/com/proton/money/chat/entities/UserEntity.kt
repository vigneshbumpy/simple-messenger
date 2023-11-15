package com.proton.money.chat.entities

import lombok.AllArgsConstructor
import javax.persistence.*

/**
 * Users Table Entity
 *
 * @property id - Auto incremented id
 * @property userName - Given username
 * @property password - Base64 Encoded password
 * @constructor Create empty Users
 */
@Entity
@Table(name= "Users")
@AllArgsConstructor
class Users (
    //mark id as primary key
    @Id //defining id as column name
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    //defining userName as column name
    @Column(nullable = false)
    val userName: String? = null,

    //defining password as column name
    @Column(nullable = false)
    val password: String? = null
)