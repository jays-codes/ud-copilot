package jayslabs.copilot.clinicalsapi.service;

import java.util.List;

import jayslabs.copilot.clinicalsapi.dto.PatientDTO;

public interface IPatientService {
    PatientDTO createPatient(PatientDTO patientDTO);
    PatientDTO getPatientById(Long id);
    List<PatientDTO> getAllPatients();
    boolean updatePatient(PatientDTO patientDTO);
    boolean deletePatient(Long id);
}