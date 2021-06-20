package com.mycompany.usermanagement.infrastructure.config

import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.QueueBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class QueueConfig {

    @Bean
    fun createUserEventQueue(): Queue {
        return QueueBuilder
            .durable("user-management.created")
            .build()
    }

    @Bean
    fun updateUserEventQueue(): Queue {
        return QueueBuilder
            .durable("user-management.updated")
            .build()
    }

    @Bean
    fun deleteUserEventQueue(): Queue {
        return QueueBuilder
            .durable("user-management.deleted")
            .build()
    }
}