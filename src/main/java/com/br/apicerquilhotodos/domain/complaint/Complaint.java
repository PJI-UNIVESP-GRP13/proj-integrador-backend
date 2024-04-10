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
    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] photo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "services_id")
    private Service service;

}
