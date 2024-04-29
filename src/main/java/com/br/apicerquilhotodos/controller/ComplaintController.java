package com.br.apicerquilhotodos.controller;

import com.br.apicerquilhotodos.domain.complaint.Complaint;
import com.br.apicerquilhotodos.domain.complaint.ComplaintDataRegister;
import com.br.apicerquilhotodos.domain.complaint.ComplaintDetailsDate;
import com.br.apicerquilhotodos.domain.complaint.Status;
import com.br.apicerquilhotodos.domain.service.Service;
import com.br.apicerquilhotodos.domain.user.User;
import com.br.apicerquilhotodos.repository.ComplaintRepository;
import com.br.apicerquilhotodos.repository.ServiceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("complaints")
public class ComplaintController {

    private final ComplaintRepository complaintRepository;
    private final ServiceRepository serviceRepository;

    public ComplaintController(ComplaintRepository complaintRepository, ServiceRepository serviceRepository) {
        this.complaintRepository = complaintRepository;
        this.serviceRepository = serviceRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestParam("photo") MultipartFile photo,
                                   @RequestParam("json_data") String jsonData,
                                   UriComponentsBuilder uriBuilder) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        // Convert MultipartFile to byte[]
        byte[] photoBytes = photo.getBytes();

        // Deserialize the JSON data
        ObjectMapper objectMapper = new ObjectMapper();
        ComplaintDataRegister complaintData = objectMapper.readValue(jsonData, ComplaintDataRegister.class);

        // Retrieve the Service entity based on the serviceId
        Service service = serviceRepository.findById(complaintData.idService())
                .orElseThrow(() -> new RuntimeException("Service not found"));

        var complaint = new Complaint(complaintData);
        complaint.setUser(user);
        complaint.setService(service);
        complaint.setStatus(Status.EM_ABERTO);
        complaint.setPhoto(photoBytes);
        complaintRepository.save(complaint);

        var uri = uriBuilder.path("/complaints/{id}").buildAndExpand(complaint.getId()).toUri();
        return ResponseEntity.created(uri).body(new ComplaintDetailsDate(complaint));
    }

    @GetMapping
    public List<ComplaintDetailsDate> listComplaintsForCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = ((User) authentication.getPrincipal()).getId();

        return complaintRepository.findByUserId(userId);
    }

}
