package ro.siit.medissity.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity(name = "medical_imaging")
public class MedicalImaging {
    @Id
    UUID id;
    @Column(nullable = false, unique = true)
    String name;
    public MedicalImaging(UUID id, String name) {
        this.id = id;
        this.name = name;
    }
    public MedicalImaging() {
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
