package org.example.kotlinboot.repository

import org.example.kotlinboot.model.Role
import org.example.kotlinboot.model.User
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class UserRepository (
    private val encoder: PasswordEncoder
){

    private  val users = mutableListOf(
        User(
            id = UUID.randomUUID(),
            email = "test@example.com",
            password = encoder.encode("test"),
            role = Role.USER,
        ),
        User(
            id = UUID.randomUUID(),
            email = "test2@example.com",
            password = encoder.encode("test2"),
            role = Role.MODERATOR,
        ),
        User(
            id = UUID.randomUUID(),
            email = "test3@example.com",
            password = encoder.encode("test3"),
            role = Role.ADMIN,
        )
    )
    fun save(user: User): Boolean {
        val updated = user.copy(password = encoder.encode(user.password))
        return users.add(updated)
    }

    fun findByEmail(email: String): User? =
        users.firstOrNull{ it.email == email }

    fun findAll(): List<User> =
        users

    fun findByUUId(uuid: UUID): User? =
        users.firstOrNull{ it.id == uuid}

    fun deleteByUUID(uuid: UUID): Boolean {
        val foundUser = findByUUId(uuid)

        return foundUser?.let {
            users.remove(it)
        }?:false
    }
}