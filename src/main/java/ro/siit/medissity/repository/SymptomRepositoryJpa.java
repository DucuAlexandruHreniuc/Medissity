package ro.siit.medissity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.siit.medissity.model.Symptom;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface SymptomRepositoryJpa extends JpaRepository<Symptom, UUID> {
    Optional<Symptom> findByName(String name);

}
