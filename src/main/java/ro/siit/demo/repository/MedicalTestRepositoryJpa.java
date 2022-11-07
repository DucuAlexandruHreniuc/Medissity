package ro.siit.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.siit.demo.model.MedicalTest;

import java.util.UUID;

@Repository
public interface MedicalTestRepositoryJpa extends JpaRepository<MedicalTest, UUID> {
}
