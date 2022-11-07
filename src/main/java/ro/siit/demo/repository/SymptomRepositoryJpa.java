package ro.siit.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.siit.demo.model.Symptom;

import java.util.UUID;

public interface SymptomRepositoryJpa extends JpaRepository<Symptom, UUID> {
}
