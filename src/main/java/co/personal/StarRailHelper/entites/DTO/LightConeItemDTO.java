package co.personal.StarRailHelper.entites.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LightConeItemDTO {

    private String name;
    private int collected;
    private List<Integer> needed;
    private List<String> lightConesName;
    private List<String> charactersName;
}
