package com.mycompany.usermanagement.application

import com.mycompany.usermanagement.infrastructure.messaging.MessagingUserEventSender
import com.mycompany.usermanagement.model.NotFoundException
import com.mycompany.usermanagement.model.User
import com.mycompany.usermanagement.model.UserAlreadyExistsException
import com.mycompany.usermanagement.model.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    val userRepository: UserRepository,
    val eventSender: MessagingUserEventSender
) {

    fun create(user: User): User {
        if(userRepository.existsByEmail(user.email) || userRepository.existsByCpf(user.cpf)){
            throw UserAlreadyExistsException()
        }
        val savedUser = userRepository.save(user)
        eventSender.create(user)
        return savedUser
    }

    fun update(id: Long, user: User) {
        val userFound = userRepository.findById(id)
        if (!userFound.isPresent()) {
            throw NotFoundException()
        }
        user.id = id
        user.address.id = userFound.get().address.id
        userRepository.save(user)
        eventSender.update(user)
    }

    fun delete(id: Long) {
        val user = userRepository.findById(id)
        if (user.isPresent) {
            userRepository.deleteById(id)
            eventSender.create(user.get())
        }
        throw NotFoundException()
    }

}