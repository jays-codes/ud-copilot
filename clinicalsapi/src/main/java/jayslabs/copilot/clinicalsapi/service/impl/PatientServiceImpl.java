package jayslabs.copilot.clinicalsapi.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import jayslabs.copilot.clinicalsapi.dto.PatientDTO;
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
        PatientDTO patientDTO = patientMapper.toDTO(patient);
        patientDTO.setClinicalDataIds(getClinicalDataByPatientId(id));
        return patientDTO;
    }

    private List<Long> getClinicalDataByPatientId(Long id) {  
        return cdrepo.findByPatientId(id)
            .stream().map(cd -> cd.getId()).collect(Collectors.toList());
    }

    @Override
    public List<PatientDTO> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();

        List<PatientDTO> patientdtos =  patients.stream().map(patientMapper::toDTO).collect(Collectors.toList());
        patientdtos.forEach(patientdto -> {
            patientdto.setClinicalDataIds(
                getClinicalDataByPatientId(patientdto.getId())
            );
        });
        
        return patientdtos;
        //return patients.stream().map(patientMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public boolean updatePatient(PatientDTO patientDTO) {
        Long id = patientDTO.getId();
        
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new RuntimeException("Patient not found"));
        patient.setFirstName(patientDTO.getFirstName());
        patient.setLastName(patientDTO.getLastName());
        patient.setAge(patientDTO.getAge());

        patientRepository.save(patient);

        return true;
    }

    @Override
    public boolean deletePatient(Long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new RuntimeException("Patient not found"));
        patientRepository.delete(patient);
        return true;
    }
}