package ro.siit.medissity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.siit.medissity.model.MedicalImaging;

import java.util.Optional;
import java.util.UUID;

public interface MedicalImagingRepositoryJpa extends JpaRepository<MedicalImaging, UUID> {
    Optional<MedicalImaging> findByName(String name);
}
