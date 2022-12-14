package ro.siit.medissity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.siit.medissity.model.MedicalImaging;
import ro.siit.medissity.model.MedicalTest;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MedicalTestRepositoryJpa extends JpaRepository<MedicalTest, UUID> {
    Optional<MedicalTest> findByName(String name);

}
