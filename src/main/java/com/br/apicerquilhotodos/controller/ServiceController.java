package com.br.apicerquilhotodos.controller;

import com.br.apicerquilhotodos.domain.service.Service;
import com.br.apicerquilhotodos.domain.service.ServiceDataRegister;
import com.br.apicerquilhotodos.domain.service.ServiceDetailsDate;
import com.br.apicerquilhotodos.domain.service.UpdateServiceData;
import com.br.apicerquilhotodos.repository.ServiceRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("services")
public class ServiceController {

    private final ServiceRepository repository;

    public ServiceController(ServiceRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid ServiceDataRegister data, UriComponentsBuilder uriBuilder) {
        var service = new Service(data);
        repository.save(service);
        var uri = uriBuilder.path("/services/{id}").buildAndExpand(service.getId()).toUri();
        return ResponseEntity.created(uri).body(new ServiceDetailsDate(service));
    }

    @GetMapping
    public ResponseEntity<List<Service>> listAll() {
        var services = repository.findAll();
        return ResponseEntity.ok(services);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid UpdateServiceData data) {
        var service = repository.getReferenceById(data.id());
        service.infoUpdate(data);
        return ResponseEntity.ok(new ServiceDetailsDate(service));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable String id) {
        var service = repository.getReferenceById(id);
        repository.delete(service);
        return ResponseEntity.noContent().build();
    }

}
