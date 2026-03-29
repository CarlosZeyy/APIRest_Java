package br.com.carlosmoises.apirestusers.Controllers;

import br.com.carlosmoises.apirestusers.entity.User;
import br.com.carlosmoises.apirestusers.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Cria um novo usuário", description = "Recebe dados do usuário, criptgrafa a senha e salva no banco de dados MySQL")
    @PostMapping
    public ResponseEntity<MessageResponse> createUser(@RequestBody CreateUserDto createUserDto) {
        var userId = userService.createUser(createUserDto);

        var response = new MessageResponse("Usuário criado com sucesso");
        return ResponseEntity.created(URI.create("/users/" + userId)).body(response);
    }

    @Operation(summary = "Busca usuário por ID", description = "Retorna os detalhes de um usuário específico através do seu UUID.")
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId) {
        var user = userService.getUserById(userId);

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            throw new EntityNotFoundException("Erro: ID do usuário não encontrado");
        }
    }

    @Operation(summary = "Busca todos os usuários", description = "Retorna os detalhes de todos os usuários cadastrados")
    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        var users = userService.getUsers();

        return ResponseEntity.ok(users);
    }

    @Operation(summary = "Busca usuário por ID e permite fazer a alteração de nome e senha", description = "Permite a mudança de dados de um usuário específico através do seu UUID.")
    @PutMapping("/{userId}")
    public ResponseEntity<MessageResponse> updateById(@PathVariable("userId") String userId, @RequestBody UpdateUserDto updateUserDto) {
        userService.updateUserById(userId, updateUserDto);

        var response = new MessageResponse("Usuário atualizado com sucesso!");

        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Busca usuário por ID e apaga ele do banco de dados", description = "Apaga tudo de um usuário específico através do seu UUID.")
    @DeleteMapping("/{userId}")
    public ResponseEntity<MessageResponse> deleteById(@PathVariable("userId") String userId) {
        userService.deleteUserById(userId);

        var response = new MessageResponse("Usuário apagado com sucesso!");

        return ResponseEntity.ok().body(response);
    }
}
