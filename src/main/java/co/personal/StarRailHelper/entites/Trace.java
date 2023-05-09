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
@Table(name = "Traces")
public class Trace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "trace")
    private List<TraceItem> tracesItems;

    @ManyToOne
    @JsonIgnore
    private Character character;

    public Trace(String name, Character character) {
        this.name = name;
        this.character = character;
    }
}
