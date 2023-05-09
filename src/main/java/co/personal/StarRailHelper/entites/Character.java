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
@Table(name = "Characters")
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String details;

    @ManyToMany(mappedBy = "character")
    @JsonIgnore
    private List<AscensionItem> ascensionItems;

    private String ascensionItem1;
    private Integer needed1;

    private String ascensionItem2;
    private Integer needed2;

    @OneToOne
    private LightCone lightCone;

    @OneToMany(mappedBy = "character")
    private List<Trace> trace;

    public Character(String name, String details) {
        this.name = name;
        this.details = details;
    }
}
