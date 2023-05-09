package co.personal.StarRailHelper.entites;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Ascension_Items")
public class AscensionItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int collected;

    @ManyToMany
    private List<Character> character;

    @ManyToOne
    private LightCone lightCone;

    public AscensionItem(String name, int collected, List<Character> character) {
        this.name = name;
        this.collected = collected;
        this.character = character;
    }

    public AscensionItem(String name, int collected, LightCone lightCone) {
        this.name = name;
        this.collected = collected;
        this.lightCone = lightCone;
    }
}
