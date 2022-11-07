package ro.siit.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.siit.demo.model.MedicalImaging;

import java.util.UUID;

public interface MedicalImagingRepositoryJpa extends JpaRepository<MedicalImaging, UUID> {
}
