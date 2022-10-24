package com.example.cricketer.domain

import javax.persistence.*

@Entity
@Table(name="cricketers")
data class Cricketer (
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cricketer_id_seq_gen" )
    @SequenceGenerator(name = "cricketer_id_seq_gen", sequenceName = "cricketer_id_seq")
    val id: Long?,
    @Column(nullable = false)
    val name: String,
    @Column(nullable = false)
    val country: String,
    val highestScore: Number
)