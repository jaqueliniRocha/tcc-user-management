package com.mycompany.usermanagement.infrastructure.rest
import com.mycompany.usermanagement.model.NotFoundException
import com.mycompany.usermanagement.model.User
import com.mycompany.usermanagement.model.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.http.HttpEntity
import org.springframework.http.ResponseEntity.*
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import java.util.function.Consumer
import javax.validation.Valid
import javax.websocket.server.PathParam


@RestController
@RequestMapping("user")
class UserApi(
    private val userRepository: UserRepository
) {

    private val log = LoggerFactory.getLogger(javaClass)

    @PostMapping
    fun create(
        @Valid @RequestBody user: User,
        uriComponentsBuilder: UriComponentsBuilder
    ): HttpEntity<Any?> {
        log.info("creating user $user")
        val savedUser = userRepository.save(user)
        log.info("finished with id $user.id")
        return created(uriComponentsBuilder.path("/user/{id}").buildAndExpand(savedUser.id).toUri()).build()
    }

    @DeleteMapping("/{id}")
    fun delete(
        @PathVariable id: Long
    ): HttpEntity<Any?> {
        log.info("deleting user id $id")
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id)
            log.info("deleted user id $id")
            return noContent().build()
        }
        throw NotFoundException()
    }
}