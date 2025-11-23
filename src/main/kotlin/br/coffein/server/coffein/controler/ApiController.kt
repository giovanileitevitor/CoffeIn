package br.coffein.server.coffein.controler

import br.coffein.server.coffein.model.ApiDetails
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class ApiController(

) {
    @GetMapping("/details")
    fun testApi(): ApiDetails {
        return ApiDetails()
    }
}