package com.br.apicerquilhotodos.domain.service;

public record ServiceDetailsGet (String id, String service) {
    public ServiceDetailsGet(Service service) {
        this(service.getId(), service.getService());
    }
}
