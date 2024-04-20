package com.br.apicerquilhotodos.domain.complaint;

public record ComplaintDetailsDate(String id,
                                   String address,
                                   String neighborhood,
                                   String zipCode,
                                   String description
) {

    public ComplaintDetailsDate(Complaint complaint) {
        this(complaint.getId(), complaint.getAddress(), complaint.getNeighborhood(), complaint.getZipCode(), complaint.getDescription());
    }
}
