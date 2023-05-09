package co.personal.StarRailHelper.services;

import co.personal.StarRailHelper.entites.AscensionItem;
import co.personal.StarRailHelper.entites.Character;
import co.personal.StarRailHelper.entites.DTO.AscensionItemDTO;
import co.personal.StarRailHelper.entites.LightCone;
import co.personal.StarRailHelper.exceptions.AscensionItemNotFoundException;
import co.personal.StarRailHelper.exceptions.CharacterNotFoundException;
import co.personal.StarRailHelper.exceptions.LightConeNotFoundException;
import co.personal.StarRailHelper.repositories.AscensionItemRepository;
import co.personal.StarRailHelper.repositories.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

@Service
public class AscensionItemService {

    @Autowired
    private CharacterService characterService;

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private LightConeService lightConeService;

    @Autowired
    private AscensionItemRepository ascensionItemRepository;

    private AscensionItem getAscensionItemsById(Long id) throws AscensionItemNotFoundException {
        Optional<AscensionItem> optionalAscensionItems = ascensionItemRepository.findById(id);
        if(optionalAscensionItems.isPresent()){
            return optionalAscensionItems.get();
        } else {
            throw new AscensionItemNotFoundException("Ascension Item with id " + id + " not found.");
        }
    }

    public AscensionItem saveForCharacter(AscensionItemDTO ascensionItemDTO) throws CharacterNotFoundException {
        Character character = characterService.readByName(ascensionItemDTO.getCharactersName().get(0));

        List<Character> characterList = new ArrayList<>();
        characterList.add(character);

        Integer needed = ascensionItemDTO.getNeeded().get(0);

        if(character.getAscensionItem1() == null){
            character.setAscensionItem1(ascensionItemDTO.getName());
            character.setNeeded1(needed);
        } else {
            character.setAscensionItem2(ascensionItemDTO.getName());
            character.setNeeded2(needed);
        }

        characterRepository.save(character);

        AscensionItem ascensionItem = new AscensionItem(
                ascensionItemDTO.getName(),
                ascensionItemDTO.getCollected(),
                characterList
        );

        return ascensionItemRepository.save(ascensionItem);
    }

    public AscensionItem saveForTwoCharacter(AscensionItemDTO ascensionItemDTO) throws CharacterNotFoundException, LightConeNotFoundException {

        //Searching Character by Name
        Character character1 = characterService.readByName(ascensionItemDTO.getCharactersName().get(0));
        Character character2 = characterService.readByName(ascensionItemDTO.getCharactersName().get(1));

        List<String> charactersName = new ArrayList<>();
        charactersName.add(character1.getName());
        charactersName.add(character2.getName());

        Integer needed1 = ascensionItemDTO.getNeeded().get(0);
        Integer needed2 = ascensionItemDTO.getNeeded().get(1);

        List<Integer> needed = new ArrayList<>();
        needed.add(needed1);
        needed.add(needed2);

        // if the needed number is not equal
        if(character1.getAscensionItem1() == null){
            character1.setAscensionItem1(ascensionItemDTO.getName());
            character1.setNeeded1(needed1);
        } else {
            character1.setAscensionItem2(ascensionItemDTO.getName());
            character1.setNeeded2(needed1);
        }

        if(character2.getAscensionItem1() == null) {
            character2.setAscensionItem1(ascensionItemDTO.getName());
            character2.setNeeded1(needed2);
        } else {
            character2.setAscensionItem2(ascensionItemDTO.getName());
            character2.setNeeded2(needed2);
        }

        List<Character> characters = new ArrayList<>();
        characters.add(character1);
        characters.add(character2);

        characterRepository.save(character1);
        characterRepository.save(character2);

        AscensionItem ascensionItem = new AscensionItem(
                ascensionItemDTO.getName(),
                ascensionItemDTO.getCollected(),
                characters);
        return ascensionItemRepository.save(ascensionItem);
    }

    /*public AscensionItem saveForLightCone(AscensionItemDTO ascensionItemDTO) throws CharacterNotFoundException, LightConeNotFoundException {

        LightCone lightCone = lightConeService.readByName(ascensionItemDTO.getLightConeName());

        Integer needed1 = ascensionItemDTO.getNeeded1();
        Integer needed2 = ascensionItemDTO.getNeeded2();

        Integer needed = needed1 + needed2;

        AscensionItem ascensionItem = new AscensionItem(
                ascensionItemDTO.getName(),
                ascensionItemDTO.getCollected(),
                needed,
                lightCone);
        return ascensionItemRepository.save(ascensionItem);
    }*/

    public AscensionItem read(Long id) throws AscensionItemNotFoundException {
        return getAscensionItemsById(id);
    }

    public String readByNeeded(String name) throws AscensionItemNotFoundException, CharacterNotFoundException {
        AscensionItem ascensionItem = readByName(name);

        Character character1 = characterService.readByName(ascensionItem.getCharacter().get(0).getName());
        Character character2 = characterService.readByName(ascensionItem.getCharacter().get(1).getName());

        Integer neededAsIt1 = character1.getNeeded1();
        Integer neededAsIt2 = character2.getNeeded1();
        int collectedAsIt = ascensionItem.getCollected();
        if(collectedAsIt > neededAsIt1 + neededAsIt2) {
            return "You have enough items " + "\n"+
                    "Collected = " + collectedAsIt + "\n"+
                    "Needed for character " + ascensionItem.getCharacter().get(0).getName() + " = " + neededAsIt1 + "\n"+
                    "Needed for character " + ascensionItem.getCharacter().get(1).getName() + " = " + neededAsIt2;
        } else if(neededAsIt1 + neededAsIt2 > collectedAsIt) {
            int need = neededAsIt1 + neededAsIt2 -  collectedAsIt;
            return "You need " + need + " " + name + " ascension items" + "\n"+
                    "Needed for character " + ascensionItem.getCharacter().get(0).getName() + " = " + neededAsIt1 + "\n"+
                    "Needed for character " + ascensionItem.getCharacter().get(1).getName() + " = " + neededAsIt2;
        } else {
            throw new AscensionItemNotFoundException("Item with name " + name + " not found");
        }
    }

    public AscensionItem update(Long id, AscensionItem ascensionItem) throws AscensionItemNotFoundException {
        AscensionItem ascensionItemUpdated = getAscensionItemsById(id);
        ascensionItemUpdated.setName(ascensionItem.getName());
        ascensionItemUpdated.setCollected(ascensionItem.getCollected());
        ascensionItemUpdated.setCharacter(ascensionItem.getCharacter());
        ascensionItemUpdated.setLightCone(ascensionItem.getLightCone());
        return ascensionItemRepository.save(ascensionItemUpdated);
    }

    public void delete(Long id) throws AscensionItemNotFoundException {
        AscensionItem ascensionItem = getAscensionItemsById(id);
        ascensionItemRepository.delete(ascensionItem);
    }

    public AscensionItem readByName(String name) throws AscensionItemNotFoundException {
        Optional<AscensionItem> optionalAscensionItems = ascensionItemRepository.findByName(name);
        if(optionalAscensionItems.isPresent()) {
            return optionalAscensionItems.get();
        } else {
            throw new AscensionItemNotFoundException("Ascension Item with name " + name + " not found");
        }
    }
}
