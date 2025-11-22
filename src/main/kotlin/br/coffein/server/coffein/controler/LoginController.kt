package br.coffein.server.coffein.controler

import br.coffein.server.coffein.dto.request.LoginRequest
import br.coffein.server.coffein.dto.request.NewLoginRequest
import br.coffein.server.coffein.dto.response.LoginResponse
import br.coffein.server.coffein.service.LoginService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/login")
class LoginController(
    private val loginService: LoginService
) {

    @PostMapping
    fun startLogin(
        @RequestBody @Valid form: LoginRequest
    ): LoginResponse {
       return loginService.checkLogin(
           loginRequest = form
       )
    }

    @PostMapping("/new")
    fun createLogin(
        @RequestBody @Valid form: NewLoginRequest
    ): LoginResponse {
        return loginService.createLogin(
            newLogin = form
        )
    }

//    @GetMapping
//    fun startLogin(): LoginResponse{
//        return LoginResponse(
//            validLogin = true,
//            code = 10,
//            email = "giovanileitevitor@gmail.com"
//        )
//    }


//    @GetMapping("/{id}")
//    fun buscarPorId(@PathVariable id: Long): TopicoView {
//        return service.buscarPorId(id)
//    }
//
//
//
//    @PutMapping
//    fun atualizar(@RequestBody @Valid form: AtualizacaoTopicoForm): ResponseEntity<TopicoView> {
//        val topicoView = service.atualizar(form)
//        return ResponseEntity.ok(topicoView)
//    }
//
//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    fun deletar(@PathVariable id: Long) {
//        service.deletar(id)
//    }

}