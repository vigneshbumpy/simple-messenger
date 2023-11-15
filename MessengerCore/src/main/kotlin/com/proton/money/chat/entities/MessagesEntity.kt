package com.proton.money.chat.entities

import lombok.AllArgsConstructor
import javax.persistence.*

/**
 * Messages Table
 *
 * @property id - Auto incremented id
 * @property fromUserName - Message sent from Username
 * @property toUserName- Message sent to Username
 * @property text - Message that is sent
 * @property isRead - Maker for message is read or not /* Todo: Change this to enum in the future if required to support
 * delivered/seen functionality*/
 * @constructor Create empty Messages
 */
@Entity
@Table(name= "Messages")
@AllArgsConstructor
class Messages (
    //mark id as primary key
    @Id //defining id as column name
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    //defining fromUserName as column name
    @Column(nullable = false)
    val fromUserName: String? = null,

    //defining toUserName as column name
    @Column(nullable = false)
    val toUserName: String? = null,

    //defining text as column name
    @Column(nullable = false)
    val text: String? = null,

    //defining is_read as column name
    @Column(nullable = false)
    val isRead: Boolean? = false
)