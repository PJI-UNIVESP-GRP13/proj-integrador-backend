package com.br.apicerquilhotodos.repository;

import com.br.apicerquilhotodos.domain.service.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, String> {
}
