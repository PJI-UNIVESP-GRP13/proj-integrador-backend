package com.br.apicerquilhotodos.repository;

import com.br.apicerquilhotodos.domain.service.Service;
import com.br.apicerquilhotodos.domain.service.ServiceDetailsGet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, String> {

    @Query("SELECT new com.br.apicerquilhotodos.domain.service.ServiceDetailsGet(s) FROM Service s")
    List<ServiceDetailsGet> findAllServices();

}
