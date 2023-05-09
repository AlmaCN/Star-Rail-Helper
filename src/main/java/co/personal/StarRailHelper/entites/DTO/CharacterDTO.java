package co.personal.StarRailHelper.entites.DTO;

import lombok.*;
import org.springframework.lang.Nullable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CharacterDTO {

    private String name;
    private String details;
    @Nullable
    private int needed;
    @Nullable
    private String ascensionItemName;

    public CharacterDTO(int needed, String ascensionItemName) {
        this.needed = needed;
        this.ascensionItemName = ascensionItemName;
    }
}
