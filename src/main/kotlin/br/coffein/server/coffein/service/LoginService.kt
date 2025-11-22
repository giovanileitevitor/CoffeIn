package br.coffein.server.coffein.service

import br.coffein.server.coffein.dto.request.LoginRequest
import br.coffein.server.coffein.dto.request.NewUserRequest
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
        print("size: ${users.size}")
        return if(users.isNotEmpty()){
            LoginResponse(
                validLogin = true,
                email = loginRequest.email,
                code = 200
            )
        } else {
            LoginResponse(
                validLogin = false,
                email = loginRequest.email,
                code = 404
            )
        }
    }

    fun createLogin(newUser: NewUserRequest): LoginResponse{
        val login = Login(
            username = newUser.email,
            email = newUser.email,
            password = newUser.pass
        )
        loginRepository.save(login)
        return LoginResponse(
            validLogin = true,
            email = newUser.email,
            code = 201
        )
    }
}