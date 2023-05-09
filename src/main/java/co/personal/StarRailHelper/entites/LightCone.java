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

    @ManyToMany(mappedBy = "lightCone")
    @JsonIgnore
    private List<LightConeItem> lightConeItems;

    private String ascensionItem1;
    private Integer needed1;

    private String ascensionItem2;
    private Integer needed2;

    @OneToOne
    @JsonIgnore
    private Character character;

    public LightCone(String name, String details, Character character) {
        this.name = name;
        this.details = details;
        this.character = character;
    }
}
