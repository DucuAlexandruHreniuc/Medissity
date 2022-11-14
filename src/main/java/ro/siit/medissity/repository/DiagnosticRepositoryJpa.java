package ro.siit.medissity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.siit.medissity.model.Diagnostic;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DiagnosticRepositoryJpa extends JpaRepository<Diagnostic, UUID> {
        Optional<Diagnostic> findByName (String name);
    }

