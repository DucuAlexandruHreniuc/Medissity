package ro.siit.medissity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.siit.medissity.model.Symptom;

import java.util.Optional;
import java.util.UUID;

public interface SymptomRepositoryJpa extends JpaRepository<Symptom, UUID> {
    Optional<Symptom> findByName(String name);

}
