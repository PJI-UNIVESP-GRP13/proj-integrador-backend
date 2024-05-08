package com.br.apicerquilhotodos.domain.complaint;

import com.br.apicerquilhotodos.domain.service.Service;
import com.br.apicerquilhotodos.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "complaints")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String address;
    private String neighborhood;
    @Column(name = "zip_code")
    private String zipCode;
    private String description;

    @Column(columnDefinition = "bytea")
    private byte[] photo;
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "services_id")
    private Service service;

    public Complaint(ComplaintDataRegister complaintData) {
        this.address = complaintData.address();
        this.neighborhood = complaintData.neighborhood();
        this.zipCode = complaintData.zipCode();
        this.description = complaintData.description();
    }

}
