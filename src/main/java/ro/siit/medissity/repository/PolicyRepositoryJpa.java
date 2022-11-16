package ro.siit.medissity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.siit.medissity.model.Policy;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface PolicyRepositoryJpa extends JpaRepository<Policy, UUID> {
    Optional<Policy> findByName(String name);

}
