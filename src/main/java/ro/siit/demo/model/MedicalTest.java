package ro.siit.demo.model;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "medicalTests")

public class MedicalTest {
    @Id
    private UUID id;
    @Column(nullable = false, unique = true)
    private String name;

    public MedicalTest() {
    }

    public MedicalTest(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}