package com.mackenzie.cif.person.domain.dto;

import lombok.Data;

@Data
public class PatientRequest {
    private String therapistID;
    private String note;
}
