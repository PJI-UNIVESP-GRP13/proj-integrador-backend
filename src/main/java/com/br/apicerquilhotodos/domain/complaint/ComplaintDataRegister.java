package com.br.apicerquilhotodos.domain.complaint;

import jakarta.validation.constraints.NotBlank;

public record ComplaintDataRegister (
        @NotBlank
        String address,
        @NotBlank
        String neighborhood,
        @NotBlank
        String zipCode,
        @NotBlank
        String description,
        byte[] photo,
        String idService
){
        public byte[] getPhoto() {
                return photo;
        }
}
