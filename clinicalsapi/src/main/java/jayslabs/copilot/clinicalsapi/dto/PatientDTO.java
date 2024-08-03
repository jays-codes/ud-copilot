package jayslabs.copilot.clinicalsapi.dto;

import java.util.List;

import jayslabs.copilot.clinicalsapi.entity.ClinicalData;
import lombok.Data;

@Data
public class PatientDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private List<ClinicalData> clinicalData;
}