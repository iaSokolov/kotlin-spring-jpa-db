package ru.iasokolov.demo.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "countries")
class Country(
    @Id
    val id: Int,

    @Column
    val name: String
)