package com.mycompany.usermanagement.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
class Address(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,
    @field:NotBlank val street: String,
    @field:NotNull @field:Min(0) val number: Int,
    val description: String,
    @field:NotBlank val city: String,
    @field:NotBlank val state: String,
    @field:NotBlank val zipcode: String
)