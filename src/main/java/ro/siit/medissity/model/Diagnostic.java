package ro.siit.medissity.model;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table
public class Diagnostic {
    @Id
    private UUID id;
    @Column(nullable = false, unique = true)
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "diagnostic_medicalTest_relation",
            joinColumns = @JoinColumn(name="diagnostic_id"),
            inverseJoinColumns = @JoinColumn(name = "medicalTest_id" ),
            uniqueConstraints = {@UniqueConstraint(
                    columnNames = {"diagnostic_id", "medicalTest_id"})}
    )
    private List<MedicalTest> medicalTestList;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "diagnostic_symptom_relation",
            joinColumns = @JoinColumn(name = "diagnostic_id"),
            inverseJoinColumns = @JoinColumn(name = "symptom_id")

    )
    private List<Symptom> symptomList;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "diagnostic_imaging_relation",
            joinColumns = @JoinColumn(name = "diagnostic_id"),
            inverseJoinColumns = @JoinColumn(name = "medical_imaging_id")

    )
    private List<MedicalImaging> medicalImagingList;

    public List<MedicalImaging> getMedicalImagingList() {
        return medicalImagingList;
    }

    public List<Symptom> getSymptomList() {
        return symptomList;
    }

    public String getSymptomNames() {
        return symptomList.stream()
                .map(Symptom::getName)
                .sorted()
                .collect(Collectors.joining(", "));
    }
    public String getMedicalImagingNames() {
        return medicalImagingList.stream()
                .map(MedicalImaging::getName)
                .sorted()
                .collect(Collectors.joining(", "));
    }

    public List<MedicalTest> getMedicalTestList() {
        return medicalTestList;
    }
    public String getMedicalTestNames() {
        return medicalTestList.stream()
                .map(MedicalTest::getName)
                .sorted()
                .collect(Collectors.joining(", "));
    }

    public Diagnostic(UUID id, String name) {
        this.id = id;
        this.name = name;
    }
    public Diagnostic(UUID id, String[] name) {
        this.id = id;
    }

    public Diagnostic() {
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
