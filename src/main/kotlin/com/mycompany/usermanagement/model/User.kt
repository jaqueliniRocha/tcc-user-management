package com.mycompany.usermanagement.model

import org.hibernate.annotations.Cascade
import org.hibernate.annotations.CascadeType
import org.hibernate.validator.constraints.br.CPF
import javax.persistence.*
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,
    @field:NotBlank val firstName: String,
    @field:NotBlank val lastName: String,
    @field:NotBlank val login: String,
    @field:NotBlank val password: String,
    @field:CPF val cpf: String,

    @field:NotNull
    @field:Valid
    @Cascade(CascadeType.ALL)
    @OneToOne
    val address: Address,

    @Enumerated(EnumType.STRING)
    @field:NotNull
    val category: UserCategory,

    @Enumerated(EnumType.STRING)
    @field:NotNull
    val profile: UserProfile,
) {

    override fun toString(): String {
        return "User(id=$id, firstName='$firstName', lastName='$lastName', login='$login', cpf='$cpf', " +
                "address=$address, category=$category)"
    }
}