package org.example.kotlinboot.controller.auth

data class AuthenticationRequest (
    val email: String,
    val password: String
){
}