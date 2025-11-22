package br.coffein.server.coffein.dto.response

data class LoginResponse(
    val validLogin: Boolean,
    val email: String,
    val code: Int
)
