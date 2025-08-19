package br.coffein.server.coffein.dto

data class LoginResponse(
    val loginName: String,
    val loginEmail: String,
    val loginCode: Int
)
