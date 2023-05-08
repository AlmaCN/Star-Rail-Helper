package co.personal.StarRailHelper.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Light_Cone")
public class LightCone {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String details;

    @OneToMany(mappedBy = "lightCone")
    @JsonIgnore
    private List<AscensionItems> ascensionItems;

    @OneToOne
    private Character character;

    public LightCone(String name, String details, Character character) {
        this.name = name;
        this.details = details;
        this.character = character;
    }
}
