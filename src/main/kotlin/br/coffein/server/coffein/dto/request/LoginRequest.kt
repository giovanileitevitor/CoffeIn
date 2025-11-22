package br.coffein.server.coffein.dto.request

data class LoginRequest(
    val email: String,
    val pass: String,
    val platform: String? = "Android",
    val deviceID: String
)


data class NewLoginRequest(
    val email: String,
    val pass: String,
    val platform: String? = "Android",
    val deviceID: String,
    val baseLocation: String
)
