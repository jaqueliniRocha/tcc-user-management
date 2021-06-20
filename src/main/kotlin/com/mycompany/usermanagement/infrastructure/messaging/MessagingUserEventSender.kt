package com.mycompany.usermanagement.infrastructure.messaging

import com.fasterxml.jackson.databind.ObjectMapper
import com.mycompany.usermanagement.model.User
import com.mycompany.usermanagement.model.UserEventSender
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component


@Component
class MessagingUserEventSender(
    private val rabbitTemplate: RabbitTemplate,
    private val objectMapper: ObjectMapper
) : UserEventSender {

    override fun create(user: User) {
        val userString = objectMapper.writeValueAsString(user)
        rabbitTemplate.convertAndSend("events", "user-management.created", userString.toByteArray())
    }

    override fun delete(user: User) {
        val userString = objectMapper.writeValueAsString(user)
        rabbitTemplate.convertAndSend("events", "user-management.deleted", userString.toByteArray())
    }

    override fun update(user: User) {
        val userString = objectMapper.writeValueAsString(user)
        rabbitTemplate.convertAndSend("events", "user-management.updated", userString.toByteArray())
    }
}