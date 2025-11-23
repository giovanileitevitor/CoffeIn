package br.coffein.server.coffein.controler

import br.coffein.server.coffein.model.ApiDetails
import org.h2.store.Data
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RestController
@RequestMapping("/api")
class ApiController(

) {
    @GetMapping("/details")
    fun testApi(): ApiDetails {
        val timeStamp = LocalDateTime.now()
        val formater = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")

        return ApiDetails(
            apiLastUpdate = timeStamp.format(formater)
        )
    }
}