package co.personal.StarRailHelper.entites.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TraceItemDTO {

    private String name;
    private int collected;
    private int needed;
    private String traceName;
}
