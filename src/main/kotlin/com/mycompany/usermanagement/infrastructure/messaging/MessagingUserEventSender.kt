package com.mycompany.usermanagement.infrastructure.messaging

import com.mycompany.usermanagement.model.User
import com.mycompany.usermanagement.model.UserEventSender
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component

@Component
class MessagingUserEventSender(
    private val rabbitTemplate: RabbitTemplate
) : UserEventSender {

    override fun create(user: User) {
        rabbitTemplate.convertAndSend("events", "user-management.created", user)
    }

    override fun delete(user: User) {
        rabbitTemplate.convertAndSend("events", "user-management.deleted", user)
    }

    override fun update(user: User) {
        rabbitTemplate.convertAndSend("events", "user-management.updated", user)
    }
}