package com.br.apicerquilhotodos.domain.service;

public record ServiceDetailsDate (String id, String service, Integer resolution){
    public ServiceDetailsDate(Service service) {
        this(service.getId(), service.getService(), service.getResolution());
    }
}
