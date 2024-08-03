package jayslabs.copilot.clinicalsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jayslabs.copilot.clinicalsapi.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}