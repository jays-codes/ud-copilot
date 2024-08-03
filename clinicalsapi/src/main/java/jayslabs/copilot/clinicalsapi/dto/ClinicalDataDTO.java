package jayslabs.copilot.clinicalsapi.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ClinicalDataDTO {
    private Long id;
    private String componentName;
    private String componentValue;
    private Timestamp measuredDateTime;
    private Long patientId;
}