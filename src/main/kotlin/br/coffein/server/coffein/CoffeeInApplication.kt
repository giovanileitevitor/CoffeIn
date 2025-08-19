package br.coffein.server.coffein

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CoffeeInApplication

fun main(args: Array<String>) {
	runApplication<CoffeeInApplication>(*args)
}
