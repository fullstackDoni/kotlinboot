package org.example.kotlinboot.controller.auth

data class AuthenticationResponse(
    val accessToken: String,
    val refreshToken: String,
){

}
