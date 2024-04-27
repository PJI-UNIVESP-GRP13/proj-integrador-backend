package com.br.apicerquilhotodos.repository;

import com.br.apicerquilhotodos.domain.complaint.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, String> {
    List<Complaint> findByUserId(String userId);
}
