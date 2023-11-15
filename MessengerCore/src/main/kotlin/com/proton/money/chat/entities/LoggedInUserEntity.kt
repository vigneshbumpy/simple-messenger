package com.proton.money.chat.entities

import lombok.AllArgsConstructor
import javax.persistence.*

@Entity
@Table(name= "LoggedInUsers")
@AllArgsConstructor
class LoggedInUser (
    //mark id as primary key
    @Id //defining id as column name
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    //defining userName as column name
    @Column(nullable = false)
    val userName: String? = null
)