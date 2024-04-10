package com.br.apicerquilhotodos.repository;

import com.br.apicerquilhotodos.domain.complaint.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintRepository extends JpaRepository<Complaint, String> {
}
