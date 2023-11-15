package com.proton.money.chat.entities

import lombok.AllArgsConstructor
import javax.persistence.*

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