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

        return if(users.isNotEmpty()){
            LoginResponse(
                validLogin = true,
                email = loginRequest.email,
                code = 200,
                obs = "TribeIN user found"
            )
        } else {
            LoginResponse(
                validLogin = false,
                email = loginRequest.email,
                code = 404,
                obs = "TribeIN user not found"
            )
        }
    }

    fun createLogin(newLogin: NewLoginRequest): LoginResponse{
        val login = Login(
            username = newLogin.email,
            email = newLogin.email,
            password = newLogin.pass
        )
        loginRepository.save(login)
        return LoginResponse(
            validLogin = true,
            email = newLogin.email,
            code = 201,
            obs = "TribeIN user created"
        )
    }
}