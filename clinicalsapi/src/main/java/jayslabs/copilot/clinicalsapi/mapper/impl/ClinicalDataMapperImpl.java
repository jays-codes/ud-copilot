package jayslabs.copilot.clinicalsapi.mapper.impl;

import org.springframework.stereotype.Component;

import jayslabs.copilot.clinicalsapi.dto.ClinicalDataDTO;
import jayslabs.copilot.clinicalsapi.entity.ClinicalData;
import jayslabs.copilot.clinicalsapi.mapper.ClinicalDataMapper;

@Component
public class ClinicalDataMapperImpl implements ClinicalDataMapper {

    @Override
    public ClinicalDataDTO toDto(ClinicalData clinicalData) {
        if (clinicalData == null) {
            return null;
        }

        ClinicalDataDTO clinicalDataDTO = new ClinicalDataDTO();
        clinicalDataDTO.setId(clinicalData.getId());
        clinicalDataDTO.setComponentName(clinicalData.getComponentName());
        clinicalDataDTO.setComponentValue(clinicalData.getComponentValue());
        clinicalDataDTO.setMeasuredDateTime(clinicalData.getMeasuredDateTime());
        clinicalDataDTO.setPatientId(clinicalData.getPatient().getId());

        return clinicalDataDTO;
    }

    @Override
    public ClinicalData toEntity(ClinicalDataDTO clinicalDataDTO) {
        if (clinicalDataDTO == null) {
            return null;
        }

        ClinicalData clinicalData = new ClinicalData();
        clinicalData.setComponentName(clinicalDataDTO.getComponentName());
        clinicalData.setComponentValue(clinicalDataDTO.getComponentValue());

        return clinicalData;
    }
}