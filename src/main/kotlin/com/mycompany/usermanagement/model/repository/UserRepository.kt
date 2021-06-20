package com.mycompany.usermanagement.model.repository

import com.mycompany.usermanagement.model.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<User, Long> {

    fun findByEmail(currentUserEmail: String?): User
    fun existsByEmail(email: String): Boolean
    fun existsByCpf(cpf: String): Boolean
}