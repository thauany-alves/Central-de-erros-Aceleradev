package br.com.aceleradev.java.centraldeerros.controller;

import br.com.aceleradev.java.centraldeerros.auth.TokenService;
import br.com.aceleradev.java.centraldeerros.response.CredentialsResponse;
import br.com.aceleradev.java.centraldeerros.response.TokenResponse;
import br.com.aceleradev.java.centraldeerros.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "Método para login e geração de token ")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Login com sucesso!"),
            @ApiResponse(code = 403, message = "Não tem autorização"),
            @ApiResponse(code = 500, message = "Erro interno")})
    @PostMapping(value = "/login", produces = "application/json", consumes = "application/json")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody CredentialsResponse credentialsResponse) {
        try {
            if (userService.hasAccount(credentialsResponse)) {
                String token = TokenService.create(credentialsResponse.getLogin());
                return new ResponseEntity<>(new TokenResponse(token), HttpStatus.OK);

            }
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
