package co.personal.StarRailHelper.entites;

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
@Table(name = "Ascension_Items")
public class AscensionItems {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private int collected;
    private int needed;

    @ManyToMany
    private List<Character> character;

    @ManyToOne
    private LightCone lightCone;

    public AscensionItems(String name, int collected, int needed, List<Character> character) {
        this.name = name;
        this.collected = collected;
        this.needed = needed;
        this.character = character;
    }

    public AscensionItems(String name, int collected, int needed, LightCone lightCone) {
        this.name = name;
        this.collected = collected;
        this.needed = needed;
        this.lightCone = lightCone;
    }
}
