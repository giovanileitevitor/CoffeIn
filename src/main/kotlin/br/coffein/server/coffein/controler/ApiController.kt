package br.coffein.server.coffein.controler

import br.coffein.server.coffein.model.ApiDetails
import br.coffein.server.coffein.service.BackendService
import org.h2.store.Data
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RestController
@RequestMapping("/backend")
class ApiController(
    val backendService: BackendService
) {
    @GetMapping("/status")
    fun testApi(): ApiDetails {
        return backendService.getBackendDetails()
    }
}