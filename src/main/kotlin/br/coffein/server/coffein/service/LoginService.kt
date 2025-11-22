package br.coffein.server.coffein.service

import br.coffein.server.coffein.dto.request.LoginRequest
import br.coffein.server.coffein.dto.request.NewLoginRequest
import br.coffein.server.coffein.dto.response.LoginResponse
import br.coffein.server.coffein.model.Login
import br.coffein.server.coffein.repository.LoginRepository
import org.springframework.stereotype.Service

@Service
class LoginService(
    private val loginRepository: LoginRepository
) {

    fun checkLogin(loginRequest: LoginRequest): LoginResponse{
        val users = loginRepository.findByEmail(loginRequest.email)
        if (users.isNotEmpty()) {
            val user = users.first()
            if (user.password == loginRequest.pass) {
                return LoginResponse(
                    validLogin = true,
                    email = loginRequest.email,
                    code = 200,
                    obs = "Login with success"
                )
            }
        }else{
            return LoginResponse(
                validLogin = false,
                email = loginRequest.email,
                code = 400,
                obs = "Email not found"
            )
        }

        return LoginResponse(
            validLogin = false,
            email = loginRequest.email,
            code = 404,
            obs = "Login or Password invalid"
        )
    }

    fun createLogin(newUser: NewLoginRequest): LoginResponse{
        val login = Login(
            username = newUser.email,
            email = newUser.email,
            password = newUser.pass
        )
        loginRepository.save(login)
        return LoginResponse(
            validLogin = true,
            email = newUser.email,
            code = 201,
            obs = "New User created with success"
        )
    }
}