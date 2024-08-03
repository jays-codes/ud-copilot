package jayslabs.copilot.clinicalsapi.mapper;

import jayslabs.copilot.clinicalsapi.dto.PatientDTO;
import jayslabs.copilot.clinicalsapi.entity.Patient;

public interface PatientMapper {
    PatientDTO toDTO(Patient patient);
    Patient toEntity(PatientDTO patientDTO);
}