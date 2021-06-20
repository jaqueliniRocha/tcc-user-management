package com.mycompany.usermanagement.infrastructure.rest
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("exchanges")
class ExchangeController(
    private val rabbitTemplate: RabbitTemplate
) {

    private val log = LoggerFactory.getLogger(javaClass)

    @PostMapping("{exchange}/{routingKey}")
    fun postOnExchange(
        @PathVariable exchange: String,
        @PathVariable routingKey: String,
        @RequestBody message: String
    ): HttpEntity<Any?> {
        log.info("sending message $message")
        rabbitTemplate.convertAndSend(exchange, routingKey, message)
        return ResponseEntity.ok().build()
    }
}