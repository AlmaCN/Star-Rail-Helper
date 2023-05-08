package co.personal.StarRailHelper.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Characters")
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String details;

    @ManyToMany(mappedBy = "character")
    @JsonIgnore
    private List<AscensionItems> ascensionItems;

    @OneToOne
    @JsonIgnore
    private LightCone lightCone;

    @OneToMany(mappedBy = "character")
    @JsonIgnore
    private List<Trace> trace;

    @OneToMany(mappedBy = "character")
    @JsonIgnore
    private List<Relics> relics;

    public Character(String name, String details) {
        this.name = name;
        this.details = details;
    }
}
