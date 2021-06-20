package com.mycompany.usermanagement.infrastructure.rest
import com.mycompany.usermanagement.application.UserService
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
@RequestMapping("/user")
class UserApi(
    private val userService: UserService
) {

    private val log = LoggerFactory.getLogger(javaClass)

    @PostMapping
    fun signup(
        @Valid @RequestBody user: User,
        uriComponentsBuilder: UriComponentsBuilder
    ): HttpEntity<Any?> {
        log.info("creating user $user")
        val savedUser = userService.create(user)
        log.info("finished with id $user.id")
        return created(uriComponentsBuilder.path("/user/{id}").buildAndExpand(savedUser.id).toUri()).build()
    }

    @DeleteMapping("/{id}")
    fun delete(
        @PathVariable id: Long
    ): HttpEntity<Any?> {
        log.info("deleting user id $id")
        userService.delete(id)
        return noContent().build()
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @Valid @RequestBody user: User
    ): HttpEntity<Any?> {
        log.info("updating user id $id")
        userService.update(id, user)
        return noContent().build()
    }
}