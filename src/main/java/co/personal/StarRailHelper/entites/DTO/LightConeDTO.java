package co.personal.StarRailHelper.entites.DTO;

import lombok.*;
import org.springframework.lang.Nullable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LightConeDTO {

    private String name;
    private String details;
    private String characterName;
    @Nullable
    private int needed;

    public LightConeDTO(int needed) {
        this.needed = needed;
    }
}
