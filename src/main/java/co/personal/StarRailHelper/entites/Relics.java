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
@Table(name = "Relics")
public class Relics {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "relics")
    @JsonIgnore
    private List<RelicItem> enhancementItems;

    @ManyToOne
    private Character character;

    public Relics(String name, Character character) {
        this.name = name;
        this.character = character;
    }
}
