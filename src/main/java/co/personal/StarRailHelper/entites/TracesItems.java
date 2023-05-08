package co.personal.StarRailHelper.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Traces_Items")
public class TracesItems {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private int collected;
    private int needed;

    @ManyToOne
    private Trace trace;

    public TracesItems(String name, int collected, int needed, Trace trace) {
        this.name = name;
        this.collected = collected;
        this.needed = needed;
        this.trace = trace;
    }
}
