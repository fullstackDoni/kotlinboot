package org.example.kotlinboot.controller.user

import org.example.kotlinboot.model.Role
import org.example.kotlinboot.model.User
import org.example.kotlinboot.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.util.*

@RestController
@RequestMapping("/api/user")
class UserController (
    private val userService: UserService
){
    @PostMapping
    fun create(@RequestBody userRequest: UserRequest): UserResponse =
        userService.createUser(
            user = userRequest.toModel()
        ) ?.toResponse()
            ?:throw  ResponseStatusException(HttpStatus.BAD_REQUEST,"Cannot create user")

    @GetMapping
    fun listAll():List<UserResponse> =
        userService.findAll()
            .map { it.toResponse() }

    @GetMapping("/{uuid}")
    fun findByUUID(@PathVariable uuid: UUID):UserResponse =
        userService.findByUUID(uuid)
            ?.toResponse()
            ?:throw ResponseStatusException(HttpStatus.BAD_REQUEST,"Cannot find a user.")

    @DeleteMapping("/{uuid}")
    fun deleteByUUID(@PathVariable uuid: UUID):ResponseEntity<Boolean>{
        val success = userService.deleteByUUID(uuid)
        return if(success)
            ResponseEntity.noContent()
                .build()
        else
            throw ResponseStatusException(HttpStatus.NOT_FOUND,"Cannot find a user.")
    }

    private fun UserRequest.toModel(): User =
        User (
            id = UUID.randomUUID(),
            email = this.email,
            password = this.password,
            role = Role.USER,
        )

    private fun User.toResponse(): UserResponse =
        UserResponse(
            uuid = this.id,
            email = this.email,
        )
}