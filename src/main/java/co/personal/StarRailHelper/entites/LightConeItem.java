package co.personal.StarRailHelper.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "LightCone_Items")
public class LightConeItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int collected;

    @ManyToMany
    @JsonIgnore
    private List<LightCone> lightCone;

    public LightConeItem(String name, int collected, List<LightCone> lightCone) {
        this.name = name;
        this.collected = collected;
        this.lightCone = lightCone;
    }
}
