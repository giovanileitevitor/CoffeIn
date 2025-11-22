package br.coffein.server.coffein.repository

import br.coffein.server.coffein.model.Login
import org.springframework.data.jpa.repository.JpaRepository


interface LoginRepository: JpaRepository<Login, Long> {

    fun findByEmail(email: String): List<Login>

}
