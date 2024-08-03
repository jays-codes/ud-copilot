package jayslabs.copilot.clinicalsapi.mapper;

import org.springframework.stereotype.Component;

import jayslabs.copilot.clinicalsapi.dto.PatientDTO;
import jayslabs.copilot.clinicalsapi.entity.Patient;

@Component
public class PatientMapperImpl implements PatientMapper {

    @Override
    public PatientDTO toDTO(Patient patient) {
        if (patient == null) {
            return null;
        }

        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setId(patient.getId());
        patientDTO.setFirstName(patient.getFirstName());
        patientDTO.setLastName(patient.getLastName());
        patientDTO.setAge(patient.getAge());
        patientDTO.setClinicalData(patient.getClinicalData());

        return patientDTO;
    }

    @Override
    public Patient toEntity(PatientDTO patientDTO) {
        if (patientDTO == null) {
            return null;
        }

        Patient patient = new Patient();
        patient.setId(patientDTO.getId());
        patient.setFirstName(patientDTO.getFirstName());
        patient.setLastName(patientDTO.getLastName());
        patient.setAge(patientDTO.getAge());
        patient.setClinicalData(patientDTO.getClinicalData());

        return patient;
    }
}