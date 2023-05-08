package co.personal.StarRailHelper.entites.DTO;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RelicItemDTO {

    private String name;
    private int collected;
    private int needed;
    private String relicName;
}
