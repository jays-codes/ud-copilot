package jayslabs.copilot.clinicalsapi.service;

import java.util.List;

import jayslabs.copilot.clinicalsapi.dto.ClinicalDataDTO;

public interface IClinicalDataService {
    ClinicalDataDTO createClinicalData(ClinicalDataDTO clinicalDataDTO);
    ClinicalDataDTO getClinicalDataById(Long id);
    List<ClinicalDataDTO> getAllClinicalData();
    boolean updateClinicalData(ClinicalDataDTO clinicalDataDTO);
    void deleteClinicalData(Long id);
}