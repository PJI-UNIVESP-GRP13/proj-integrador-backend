package com.br.apicerquilhotodos.domain.service;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String service;
    @Column(name = "resolution_deadline")
    private Integer resolution;

    public Service(ServiceDataRegister data) {
        this.service = data.service();
        this.resolution = data.resolution();
    }

    public void infoUpdate(UpdateServiceData data) {
        if(data.service() != null) {
            this.service = data.service();
        }
        if (data.resolution() != null) {
            this.resolution = data.resolution();
        }
    }

}
