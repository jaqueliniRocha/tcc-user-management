package com.mycompany.usermanagement.application

import com.mycompany.usermanagement.model.*
import com.mycompany.usermanagement.model.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    val userRepository: UserRepository,
    val eventSender: UserEventSender
) {

    fun create(user: User): User {
        if(userRepository.existsByEmail(user.email) || userRepository.existsByCpf(user.cpf)){
            throw UserAlreadyExistsException()
        }
        if(user.category == UserCategory.CUSTOMER && Objects.isNull(user.pets)){
            throw CustomerWithNullPetException()
        }
        val savedUser = userRepository.save(user)
        eventSender.create(user)
        return savedUser
    }

    fun update(id: Long, user: User) {
        val userFound = userRepository.findById(id)
        if (!userFound.isPresent) {
            throw NotFoundException()
        }
        user.id = id
        userRepository.save(user)
        eventSender.update(user)
    }

    fun delete(id: Long) {
        val user = userRepository.findById(id)
        if (!user.isPresent) {
            throw NotFoundException()
        }
        userRepository.deleteById(id)
        eventSender.delete(user.get())
    }

    fun findBy(userCategory: UserCategory): Collection<User>? {
        return userRepository.findByCategory(userCategory);
    }

    fun findAll(): Collection<User>? {
        return userRepository.findAll();
    }

}