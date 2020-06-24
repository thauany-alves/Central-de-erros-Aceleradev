package br.com.aceleradev.java.centraldeerros.controller;

import br.com.aceleradev.java.centraldeerros.dto.UserDTO;
import br.com.aceleradev.java.centraldeerros.mapper.UserMapper;
import br.com.aceleradev.java.centraldeerros.model.User;
import br.com.aceleradev.java.centraldeerros.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/account")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;


    @ApiOperation(value = "Este método cria uma conta de usuário")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Usuario criado com sucesso!"),
            @ApiResponse(code = 500, message = "Erro interno")})
    @PostMapping(produces = "application/json")
    public ResponseEntity<UserDTO> saveUser(@Valid @RequestBody User user){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.map(userService.save(user)));
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable(value = "id") Long id) {
        try {
            Optional<User> account = userService.findById(id);
            if (account.isPresent()) {
                return new ResponseEntity<UserDTO>(userMapper.map(account.get()), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Atualiza as informações de usuário")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Usuario atualizado"),
            @ApiResponse(code = 404, message = "Usuario não encontrado!"),
            @ApiResponse(code = 500, message = "Erro interno")})
    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<User> updateUser(@Valid @RequestBody User userUpdated, @PathVariable(value = "id") Long id) {
        try {
            Optional<User> user = userService.findById(id);
            if (user.isPresent()) {
                userUpdated.setId(user.get().getId());
                userUpdated.setCreateAt(user.get().getCreateAt());
                return new ResponseEntity<User>(userService.save(userUpdated), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
