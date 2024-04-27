package com.br.apicerquilhotodos.domain.service;

public record ServiceDetailsGet (String service) {
    public ServiceDetailsGet(Service service) {
        this(service.getService());
    }
}
