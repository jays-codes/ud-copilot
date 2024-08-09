package jayslabs.copilot.clinicalsapi.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import jayslabs.copilot.clinicalsapi.dto.PatientDTO;
import jayslabs.copilot.clinicalsapi.entity.ClinicalData;
import jayslabs.copilot.clinicalsapi.entity.Patient;
import jayslabs.copilot.clinicalsapi.mapper.PatientMapper;
import jayslabs.copilot.clinicalsapi.repository.ClinicalDataRepository;
import jayslabs.copilot.clinicalsapi.repository.PatientRepository;
import jayslabs.copilot.clinicalsapi.service.IPatientService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements IPatientService {
    private final PatientRepository patientRepository;
    private final ClinicalDataRepository cdrepo;

    private final PatientMapper patientMapper;

    @Override
    public PatientDTO createPatient(PatientDTO patientDTO) {
        Patient patient = patientMapper.toEntity(patientDTO);
        Patient savedPatient = patientRepository.save(patient);
        return patientMapper.toDTO(savedPatient);
    }

    @Override
    public PatientDTO getPatientById(Long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new RuntimeException("Patient not found"));
        return patientMapper.toDTO(patient);
    }

    @Override
    public List<PatientDTO> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(patientMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public boolean updatePatient(PatientDTO patientDTO) {
        Long id = patientDTO.getId();
        
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new RuntimeException("Patient not found"));
        patient.setFirstName(patientDTO.getFirstName());
        patient.setLastName(patientDTO.getLastName());
        patient.setAge(patientDTO.getAge());

        List<ClinicalData> clinicalData = getClinicalDataList(patientDTO.getClinicalDataIds());
        patient.setClinicalData(clinicalData);

        patientRepository.save(patient);

        return true;
    }

    private List<ClinicalData> getClinicalDataList(List<Long> clinicalDataIds) {
        return clinicalDataIds.stream()
            .map(clinicalDataId -> cdrepo.findById(clinicalDataId).orElseThrow(() -> new RuntimeException("Clinical data not found")))
            .collect(Collectors.toList());
    }

    @Override
    public boolean deletePatient(Long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new RuntimeException("Patient not found"));
        patientRepository.delete(patient);
        return true;
    }
}