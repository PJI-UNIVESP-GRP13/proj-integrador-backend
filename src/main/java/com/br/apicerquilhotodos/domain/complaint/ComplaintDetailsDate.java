package com.br.apicerquilhotodos.domain.complaint;

import java.util.Base64;

public record ComplaintDetailsDate(String address,
                                   String neighborhood,
                                   String problem,
                                   String description,
                                   String photoBase64,
                                   String status
) {

    public ComplaintDetailsDate(Complaint complaint) {
        this(complaint.getAddress(), complaint.getNeighborhood(), complaint.getService().getService(), complaint.getDescription(), Base64.getEncoder().encodeToString(complaint.getPhoto()), complaint.getStatus().toString());
    }
}
