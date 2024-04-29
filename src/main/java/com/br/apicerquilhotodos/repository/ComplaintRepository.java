package com.br.apicerquilhotodos.repository;

import com.br.apicerquilhotodos.domain.complaint.Complaint;
import com.br.apicerquilhotodos.domain.complaint.ComplaintDetailsDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, String> {

    @Query("SELECT c FROM Complaint c WHERE c.user.id = :userId")
    List<ComplaintDetailsDate> findByUserId(String userId);
}
