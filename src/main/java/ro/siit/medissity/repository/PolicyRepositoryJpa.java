package ro.siit.medissity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.siit.medissity.model.Policy;

import java.util.Optional;
import java.util.UUID;

public interface PolicyRepositoryJpa extends JpaRepository<Policy, UUID> {
    Optional<Policy> findByName(String name);

}
