package jayslabs.copilot.clinicalsapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jayslabs.copilot.clinicalsapi.dto.PatientDTO;
import jayslabs.copilot.clinicalsapi.exception.PatientNotFoundException;
import jayslabs.copilot.clinicalsapi.service.IPatientService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final IPatientService patientService;

    @PostMapping
    public ResponseEntity<PatientDTO> createPatient(@RequestBody PatientDTO patientDTO) {
        try {
            PatientDTO createdPatient = patientService.createPatient(patientDTO);
            return ResponseEntity.ok(createdPatient);
        } catch (Exception e) {
            throw new RuntimeException("Error creating patient");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long id) {
        try {
            PatientDTO patient = patientService.getPatientById(id);
            if (patient == null) {
                throw new PatientNotFoundException("Patient not found with id: " + id);
            }
            return ResponseEntity.ok(patient);
        } catch (PatientNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving patient");
        }
    }

    @GetMapping
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        try {
            List<PatientDTO> patients = patientService.getAllPatients();
            return ResponseEntity.ok(patients);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving patients");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDTO> updatePatient(@PathVariable Long id, @RequestBody PatientDTO patientDTO) {
        try {
            patientDTO.setId(id);
            boolean updated = patientService.updatePatient(patientDTO);
            if (updated) {
                return ResponseEntity.ok(patientDTO);
            } else {
                throw new PatientNotFoundException("Patient not found with id: " + id);
            }
        } catch (PatientNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error updating patient");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        try {
            boolean deleted = patientService.deletePatient(id);
            if (deleted) {
                return ResponseEntity.noContent().build();
            } else {
                throw new PatientNotFoundException("Patient not found with id: " + id);
            }
        } catch (PatientNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error deleting patient");
        }
    }
}