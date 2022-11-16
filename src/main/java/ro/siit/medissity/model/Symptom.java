package ro.siit.medissity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "symptom")

public class Symptom {
    @Id
    private UUID id;
    @Column(nullable = false, unique = true)
    @NotEmpty
    private String name;

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

    public Symptom(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public Symptom(String name) {
        this.name = name;
    }

    public Symptom() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Symptom symptom = (Symptom) o;
        return id.equals(symptom.id) && name.equals(symptom.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
