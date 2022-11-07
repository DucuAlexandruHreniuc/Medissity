package ro.siit.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;
@Entity
@Table
public class Policy {
    @Id
    private UUID id;
    @Column(nullable = false, unique = true)
    @NotEmpty
    private String name;
    private String link;

    public Policy(UUID id, String name, String link) {
        this.id = id;
        this.name = name;
        this.link = link;
    }

    public Policy(){
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
