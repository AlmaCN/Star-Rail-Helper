package co.personal.StarRailHelper.entites;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Traces_Items")
public class TraceItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int collected;
    private int needed;

    @ManyToOne
    private Trace trace;

    public TraceItem(String name, int collected, int needed, Trace trace) {
        this.name = name;
        this.collected = collected;
        this.needed = needed;
        this.trace = trace;
    }
}
