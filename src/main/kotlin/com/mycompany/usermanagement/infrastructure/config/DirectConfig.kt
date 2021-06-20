package com.mycompany.usermanagement.infrastructure.config

import org.springframework.amqp.core.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DirectConfig(
    private val createUserEventQueue: Queue,
    private val updateUserEventQueue: Queue,
    private val deleteUserEventQueue: Queue
) {
    @Bean
    fun exchange(): Exchange {
        return ExchangeBuilder
            .topicExchange("events")
            .durable(true)
            .build()
    }

    @Bean
    fun createUserEventQueueBinding(): Binding {
        return BindingBuilder
            .bind(createUserEventQueue)
            .to(exchange())
            .with("user-management.created")
            .noargs()
    }

    @Bean
    fun updateUserEventQueueBinding(): Binding {
        return BindingBuilder
            .bind(updateUserEventQueue)
            .to(exchange())
            .with("user-management.updated")
            .noargs()
    }

    @Bean
    fun deleteUserEventQueueBinding(): Binding {
        return BindingBuilder
            .bind(deleteUserEventQueue)
            .to(exchange())
            .with("user-management.deleted")
            .noargs()
    }
}