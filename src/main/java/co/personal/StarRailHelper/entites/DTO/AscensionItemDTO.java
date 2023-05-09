package co.personal.StarRailHelper.entites.DTO;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AscensionItemDTO {

    private String name;
    private int collected;
    private List<Integer> needed;
    private List<String> charactersName;
    private String lightConeName;
}
