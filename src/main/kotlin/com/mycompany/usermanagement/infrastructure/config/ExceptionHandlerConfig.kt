package com.mycompany.usermanagement.infrastructure.config

import com.mycompany.usermanagement.model.NotFoundException
import com.mycompany.usermanagement.model.UserAlreadyExistsException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ExceptionHandlerConfig : ResponseEntityExceptionHandler(
) {
    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val errors: MutableMap<String, String> = HashMap()
        ex.bindingResult.allErrors.forEach { error ->
            val fieldName = (error as FieldError).field
            error.getDefaultMessage()?.let { errors[fieldName] = it }
        }
        return ResponseEntity(errors, HttpStatus.BAD_REQUEST)

    }

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFound(
        ex: NotFoundException,
    ): ResponseEntity<Any> {
        return ResponseEntity(HttpStatus.NOT_FOUND)

    }

    @ExceptionHandler(UserAlreadyExistsException::class)
    fun handleUserAlreadyExists(
        ex: UserAlreadyExistsException,
    ): ResponseEntity<Any> {
        val errors: Map<String, String> = hashMapOf(Pair("user", "CPF or email already exists"))
        return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
    }
}