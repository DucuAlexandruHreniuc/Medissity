package ro.siit.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.siit.demo.model.Policy;

import java.util.UUID;

public interface PolicyRepositoryJpa extends JpaRepository<Policy, UUID> {
}
