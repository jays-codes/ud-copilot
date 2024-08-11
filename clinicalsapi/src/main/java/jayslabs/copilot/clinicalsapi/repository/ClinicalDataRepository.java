package jayslabs.copilot.clinicalsapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jayslabs.copilot.clinicalsapi.entity.ClinicalData;

@Repository
public interface ClinicalDataRepository extends JpaRepository<ClinicalData, Long> {

    public List<ClinicalData> findByPatientId(Long id);
}