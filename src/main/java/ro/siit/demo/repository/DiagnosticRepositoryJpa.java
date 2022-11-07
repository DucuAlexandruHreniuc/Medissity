package ro.siit.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.siit.demo.model.Diagnostic;

import java.util.List;
import java.util.UUID;

@Repository
public interface DiagnosticRepositoryJpa extends JpaRepository<Diagnostic, UUID> {

    }

