package jayslabs.copilot.clinicalsapi.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import jayslabs.copilot.clinicalsapi.dto.ClinicalDataDTO;
import jayslabs.copilot.clinicalsapi.entity.ClinicalData;
import jayslabs.copilot.clinicalsapi.entity.Patient;
import jayslabs.copilot.clinicalsapi.mapper.ClinicalDataMapper;
import jayslabs.copilot.clinicalsapi.repository.ClinicalDataRepository;
import jayslabs.copilot.clinicalsapi.repository.PatientRepository;
import jayslabs.copilot.clinicalsapi.service.IClinicalDataService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClinicalDataServiceImpl implements IClinicalDataService {

    private final ClinicalDataRepository clinicalDataRepository;
    private final PatientRepository patientRepository;
    
    private final ClinicalDataMapper clinicalDataMapper;

    @Override
    public ClinicalDataDTO createClinicalData(ClinicalDataDTO clinicalDataDTO) {
        ClinicalData clinicalData = clinicalDataMapper.toEntity(clinicalDataDTO);
        Patient patient = patientRepository.findById(clinicalDataDTO.getPatientId())
            .orElseThrow(() -> new RuntimeException("Patient not found"));
        clinicalData.setPatient(patient);
        ClinicalData savedClinicalData = clinicalDataRepository.save(clinicalData);
        return clinicalDataMapper.toDto(savedClinicalData);
    }

    @Override
    public ClinicalDataDTO getClinicalDataById(Long id) {
        ClinicalData clinicalData = clinicalDataRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Clinical data not found"));
        return clinicalDataMapper.toDto(clinicalData);
    }

    @Override
    public List<ClinicalDataDTO> getAllClinicalData() {
        List<ClinicalData> clinicalDataList = clinicalDataRepository.findAll();
        return clinicalDataList.stream()
            .map(clinicalDataMapper::toDto)
            .collect(Collectors.toList());
    }

    @Override
    public boolean updateClinicalData(ClinicalDataDTO dto) {
        Long id = dto.getId();

        ClinicalData cd = clinicalDataRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Clinical data not found"));

        cd.setComponentName(dto.getComponentName());
        cd.setComponentValue(dto.getComponentValue());
        cd.setMeasuredDateTime(dto.getMeasuredDateTime());
        clinicalDataRepository.save(cd);
        return true;
    }

    @Override
    public void deleteClinicalData(Long id) {
        ClinicalData clinicalData = clinicalDataRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Clinical data not found"));
        clinicalDataRepository.delete(clinicalData);
    }
}