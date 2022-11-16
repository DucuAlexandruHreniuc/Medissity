package ro.siit.medissity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.siit.medissity.model.MedicalImaging;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface MedicalImagingRepositoryJpa extends JpaRepository<MedicalImaging, UUID> {
    Optional<MedicalImaging> findByName(String name);
}
