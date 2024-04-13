package com.br.apicerquilhotodos.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record RegisterRequestDTO (
        @NotEmpty
        String name,
        @NotEmpty
        @Email
        String email,
        @NotEmpty
        String password,
        @NotEmpty
        String phone) {
}
