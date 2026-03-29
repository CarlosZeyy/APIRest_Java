package br.com.carlosmoises.apirestusers.Controllers;

import io.swagger.v3.oas.annotations.media.Schema;

public record MessageResponse(
        @Schema(example = "Usuario registrado no sistema", description = "Mensagens quando a interação de CRUD do sistema foi bem sucessedida para facilitar comunicação com o frontend")
        String message) {

}
