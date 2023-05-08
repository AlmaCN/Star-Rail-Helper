package co.personal.StarRailHelper.entites.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AscensionItemsDTO {

    private String name;
    private int collected;
    private int needed;
    private String charactersName;
    private String lightConeName;
}
