package com.br.apicerquilhotodos.domain.service;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ServiceDataRegister (
        @NotBlank
        String service,
        @NotNull
        Integer resolution){
}
