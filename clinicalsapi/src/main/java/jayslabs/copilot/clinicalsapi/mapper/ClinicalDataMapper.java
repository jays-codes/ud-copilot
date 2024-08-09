package jayslabs.copilot.clinicalsapi.mapper;

import jayslabs.copilot.clinicalsapi.dto.ClinicalDataDTO;
import jayslabs.copilot.clinicalsapi.entity.ClinicalData;

public interface ClinicalDataMapper {
    ClinicalDataDTO toDto(ClinicalData clinicalData);
    ClinicalData toEntity(ClinicalDataDTO clinicalDataDTO);
}