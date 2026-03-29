package br.com.carlosmoises.apirestusers.Controllers;

import io.swagger.v3.oas.annotations.media.Schema;

public record UpdateUserDto(
        @Schema(example = "carlos_moises 2", description = "Permite a mudança de nome de usuário no sistema")
        String username,

        @Schema(example = "senha@56789", description = "Permite a mudança de senha de usuário no sistema")
        String password) {}
