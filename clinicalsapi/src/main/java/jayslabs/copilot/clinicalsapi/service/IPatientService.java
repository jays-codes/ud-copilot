package jayslabs.copilot.clinicalsapi.service;

import java.util.List;

import jayslabs.copilot.clinicalsapi.dto.PatientDTO;

public interface IPatientService {
    PatientDTO createPatient(PatientDTO patientDTO);
    PatientDTO getPatientById(Long id);
    List<PatientDTO> getAllPatients();
    PatientDTO updatePatient(Long id, PatientDTO patientDTO);
    void deletePatient(Long id);
}