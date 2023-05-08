package co.personal.StarRailHelper.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Enhancement_Items")
public class RelicItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private int collected;
    private int needed;

    @ManyToOne
    private Relics relics;

    public RelicItem(String name, int collected, int needed, Relics relics) {
        this.name = name;
        this.collected = collected;
        this.needed = needed;
        this.relics = relics;
    }
}
