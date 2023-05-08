package co.personal.StarRailHelper.services;

import co.personal.StarRailHelper.entites.AscensionItems;
import co.personal.StarRailHelper.entites.Character;
import co.personal.StarRailHelper.entites.DTO.AscensionItemsDTO;
import co.personal.StarRailHelper.entites.LightCone;
import co.personal.StarRailHelper.exceptions.AscensionItemsNotFoundException;
import co.personal.StarRailHelper.exceptions.CharacterNotFoundException;
import co.personal.StarRailHelper.exceptions.LightConeNotFoundException;
import co.personal.StarRailHelper.repositories.AscensionItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AscensionItemsService {

    @Autowired
    private CharacterService characterService;

    @Autowired
    private LightConeService lightConeService;

    @Autowired
    private AscensionItemsRepository ascensionItemsRepository;

    private AscensionItems getAscensionItemsById(Long id) throws AscensionItemsNotFoundException {
        Optional<AscensionItems> optionalAscensionItems = ascensionItemsRepository.findById(id);
        if(optionalAscensionItems.isPresent()){
            return optionalAscensionItems.get();
        } else {
            throw new AscensionItemsNotFoundException("Ascension Item with id " + id + " not found.");
        }
    }

    public AscensionItems saveForCharacter(AscensionItemsDTO ascensionItemsDTO) throws CharacterNotFoundException, LightConeNotFoundException {

        List<Character> character = Collections.singletonList(characterService.readByName(ascensionItemsDTO.getCharactersName()));

        AscensionItems ascensionItems = new AscensionItems(
                ascensionItemsDTO.getName(),
                ascensionItemsDTO.getCollected(),
                ascensionItemsDTO.getNeeded(),
                character);
        return ascensionItemsRepository.save(ascensionItems);
    }

    public AscensionItems saveForLightCone(AscensionItemsDTO ascensionItemsDTO) throws CharacterNotFoundException, LightConeNotFoundException {

        LightCone lightCone = lightConeService.readByName(ascensionItemsDTO.getLightConeName());

        AscensionItems ascensionItems = new AscensionItems(
                ascensionItemsDTO.getName(),
                ascensionItemsDTO.getCollected(),
                ascensionItemsDTO.getNeeded(),
                lightCone);
        return ascensionItemsRepository.save(ascensionItems);
    }

    public AscensionItems read(Long id) throws AscensionItemsNotFoundException{
        return getAscensionItemsById(id);
    }

    public AscensionItems update(Long id, AscensionItems ascensionItems) throws AscensionItemsNotFoundException{
        AscensionItems ascensionItemsUpdated = getAscensionItemsById(id);
        ascensionItemsUpdated.setName(ascensionItems.getName());
        ascensionItemsUpdated.setCollected(ascensionItems.getCollected());
        ascensionItemsUpdated.setNeeded(ascensionItems.getNeeded());
        ascensionItemsUpdated.setCharacter(ascensionItems.getCharacter());
        ascensionItemsUpdated.setLightCone(ascensionItems.getLightCone());
        return ascensionItemsRepository.save(ascensionItemsUpdated);
    }

    public void delete(Long id) throws AscensionItemsNotFoundException{
        AscensionItems ascensionItems = getAscensionItemsById(id);
        ascensionItemsRepository.delete(ascensionItems);
    }

    public AscensionItems readByName(String name) throws AscensionItemsNotFoundException{
        Optional<AscensionItems> optionalAscensionItems = ascensionItemsRepository.findByName(name);
        if(optionalAscensionItems.isPresent()) {
            return optionalAscensionItems.get();
        } else {
            throw new AscensionItemsNotFoundException("Ascension Item with name " + name + " not found");
        }
    }
}
