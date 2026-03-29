package br.com.carlosmoises.apirestusers.Controllers;

import io.swagger.v3.oas.annotations.media.Schema;

public record ErrorResponse(
        @Schema(example = "Erro ao registrar usuário no sistema", description = "Mensagens quando ocorre um erro do sistema para facilitar comunicação com o frontend")
        String message,

        @Schema(example = "Status code", description = "Status code da requisição")
        int status) {}
