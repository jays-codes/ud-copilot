package jayslabs.copilot.clinicalsapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jayslabs.copilot.clinicalsapi.dto.ClinicalDataDTO;
import jayslabs.copilot.clinicalsapi.service.IClinicalDataService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/clinicaldata")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ClinicalDataController {

    private final IClinicalDataService clinicalDataService;

    @PostMapping
    public ResponseEntity<ClinicalDataDTO> createClinicalData(@RequestBody ClinicalDataDTO clinicalDataDTO) {
        ClinicalDataDTO createdClinicalData = clinicalDataService.createClinicalData(clinicalDataDTO);
        return ResponseEntity.ok(createdClinicalData);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClinicalDataDTO> getClinicalDataById(@PathVariable Long id) {
        ClinicalDataDTO clinicalData = clinicalDataService.getClinicalDataById(id);
        return ResponseEntity.ok(clinicalData);
    }

    @GetMapping
    public ResponseEntity<List<ClinicalDataDTO>> getAllClinicalData() {
        List<ClinicalDataDTO> clinicalDataList = clinicalDataService.getAllClinicalData();
        return ResponseEntity.ok(clinicalDataList);
    }

    @PutMapping
    public ResponseEntity<Boolean> updateClinicalData(@RequestBody ClinicalDataDTO clinicalDataDTO) {
        boolean updated = clinicalDataService.updateClinicalData(clinicalDataDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClinicalData(@PathVariable Long id) {
        clinicalDataService.deleteClinicalData(id);
        return ResponseEntity.noContent().build();
    }

    //add api to retrieve clinical data by patient id
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<ClinicalDataDTO>> getClinicalDataByPatientId(@PathVariable Long patientId) {
        List<ClinicalDataDTO> clinicalDataList = clinicalDataService.getClinicalDataByPatientId(patientId);
        return ResponseEntity.ok(clinicalDataList);
    }

}