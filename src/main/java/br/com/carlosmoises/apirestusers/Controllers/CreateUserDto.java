package br.com.carlosmoises.apirestusers.Controllers;

import io.swagger.v3.oas.annotations.media.Schema;

public record CreateUserDto(
        @Schema(example = "carlos_moises", description = "Nome de usuário no sistema")
        String username,

        @Schema(example = "carlos@email.com", description = "E-mail de usuário único no sistema")
        String email,

        @Schema(example = "Senha@123", description = "A senha será criptografada antes de ser salva")
        String password) {

}
