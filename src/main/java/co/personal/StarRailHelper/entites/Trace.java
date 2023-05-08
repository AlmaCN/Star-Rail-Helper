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
@Table(name = "Traces")
public class Trace {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "trace")
    @JsonIgnore
    private List<TracesItems> tracesItems;

    @ManyToOne
    private Character character;

    public Trace(String name, Character character) {
        this.name = name;
        this.character = character;
    }
}
