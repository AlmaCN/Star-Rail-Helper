package co.personal.StarRailHelper.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Light_Cone")
public class LightCone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String details;

    @OneToMany(mappedBy = "lightCone")
    @JsonIgnore
    private List<AscensionItem> ascensionItems;

    private Integer needed;

    @OneToOne
    private Character character;

    public LightCone(String name, String details, Character character) {
        this.name = name;
        this.details = details;
        this.character = character;
    }
}
