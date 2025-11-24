package br.coffein.server.coffein.service

import br.coffein.server.coffein.model.ApiDetails
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class BackendService {

    fun getBackendDetails(): ApiDetails{
        val timeStamp = LocalDateTime.now()
        val formater = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")


        return ApiDetails(
            apiLastUpdate = timeStamp.format(formater),
            backendName = "Backend TribeIN / Backend CoffeeIN / Others"
        )
    }
}