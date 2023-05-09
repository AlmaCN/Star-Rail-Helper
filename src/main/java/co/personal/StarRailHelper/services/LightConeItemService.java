package co.personal.StarRailHelper.services;

import co.personal.StarRailHelper.entites.AscensionItem;
import co.personal.StarRailHelper.entites.Character;
import co.personal.StarRailHelper.entites.DTO.LightConeItemDTO;
import co.personal.StarRailHelper.entites.LightCone;
import co.personal.StarRailHelper.entites.LightConeItem;
import co.personal.StarRailHelper.exceptions.AscensionItemNotFoundException;
import co.personal.StarRailHelper.exceptions.CharacterNotFoundException;
import co.personal.StarRailHelper.exceptions.LightConeItemNotFoundException;
import co.personal.StarRailHelper.exceptions.LightConeNotFoundException;
import co.personal.StarRailHelper.repositories.CharacterRepository;
import co.personal.StarRailHelper.repositories.LightConeItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LightConeItemService {

    @Autowired
    private LightConeItemRepository lightConeItemRepository;

    @Autowired
    private CharacterService characterService;

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private LightConeService lightConeService;


    private LightConeItem getLightConeItemsById(Long id) throws LightConeItemNotFoundException {
        Optional<LightConeItem> optionalLightConeItem = lightConeItemRepository.findById(id);
        if(optionalLightConeItem.isPresent()){
            return optionalLightConeItem.get();
        } else {
            throw new LightConeItemNotFoundException("Light Cone Item with id " + id + " not found");
        }
    }

    public LightConeItem saveForLightCone(LightConeItemDTO lightConeItemDTO) throws LightConeNotFoundException, CharacterNotFoundException {

        Character character = characterService.readByName(lightConeItemDTO.getCharactersName().get(0));

        LightCone lightCone = lightConeService.readByName(lightConeItemDTO.getLightConesName().get(0));

        List<LightCone> lightConeList = new ArrayList<>();
        lightConeList.add(lightCone);

        Integer needed = lightConeItemDTO.getNeeded().get(0);

        if(lightCone.getAscensionItem1() == null){
            lightCone.setAscensionItem1(lightConeItemDTO.getName());
            lightCone.setNeeded1(needed);
        } else {
            lightCone.setAscensionItem2(lightConeItemDTO.getName());
            lightCone.setNeeded2(needed);
        }

        LightConeItem lightConeItem = new LightConeItem(
                lightConeItemDTO.getName(),
                lightConeItemDTO.getCollected(),
                lightConeList
        );

        character.setLightCone(lightCone);
        characterRepository.save(character);

        return lightConeItemRepository.save(lightConeItem);
    }

    public LightConeItem saveForTwoLightCone(LightConeItemDTO lightConeItemDTO) throws LightConeNotFoundException, CharacterNotFoundException {

        Character character1 = characterService.readByName(lightConeItemDTO.getCharactersName().get(0));
        Character character2 = characterService.readByName(lightConeItemDTO.getCharactersName().get(1));

        LightCone lightCone1 = lightConeService.readByName(lightConeItemDTO.getLightConesName().get(0));
        LightCone lightCone2 = lightConeService.readByName(lightConeItemDTO.getLightConesName().get(1));

        List<LightCone> lightConeList = new ArrayList<>();
        lightConeList.add(lightCone1);
        lightConeList.add(lightCone2);

        Integer needed1 = lightConeItemDTO.getNeeded().get(0);
        Integer needed2 = lightConeItemDTO.getNeeded().get(1);

        List<Integer> needed = new ArrayList<>();
        needed.add(needed1);
        needed.add(needed2);

        if(lightCone1.getAscensionItem1() == null){
            lightCone1.setAscensionItem1(lightConeItemDTO.getName());
            lightCone1.setNeeded1(needed1);
        } else {
            lightCone1.setAscensionItem2(lightConeItemDTO.getName());
            lightCone1.setNeeded2(needed1);
        }

        if(lightCone2.getAscensionItem1() == null){
            lightCone2.setAscensionItem1(lightConeItemDTO.getName());
            lightCone2.setNeeded1(needed2);
        } else {
            lightCone2.setAscensionItem2(lightConeItemDTO.getName());
            lightCone2.setNeeded2(needed2);
        }

        LightConeItem lightConeItem = new LightConeItem(
                lightConeItemDTO.getName(),
                lightConeItemDTO.getCollected(),
                lightConeList
        );

        character1.setLightCone(lightCone1);
        character2.setLightCone(lightCone2);
        characterRepository.save(character1);
        characterRepository.save(character2);

        return lightConeItemRepository.save(lightConeItem);
    }

    public LightConeItem readByName(String name) throws LightConeItemNotFoundException {
        Optional<LightConeItem> optionalLightConeItem = lightConeItemRepository.findByName(name);
        if(optionalLightConeItem.isPresent()) {
            return optionalLightConeItem.get();
        } else {
            throw new LightConeItemNotFoundException("Light Cone Item with name " + name + " not found");
        }
    }

    public LightConeItem read(Long id) throws LightConeItemNotFoundException {
        return getLightConeItemsById(id);
    }

    public String readByNeeded(String name) throws LightConeItemNotFoundException, LightConeNotFoundException {
        LightConeItem lightConeItem = readByName(name);

        LightCone lightCone1 = lightConeService.readByName(lightConeItem.getLightCone().get(0).getName());
        LightCone lightCone2 = lightConeService.readByName(lightConeItem.getLightCone().get(1).getName());

        Integer neededLC1 = lightCone1.getNeeded1();
        Integer neededLC2 = lightCone2.getNeeded2();
        int collectedLC = lightConeItem.getCollected();
        if(collectedLC > neededLC1 + neededLC2) {
            return "You have enough items " + "\n"+
                    "Collected = " + collectedLC + "\n"+
                    "Needed for light cone " + lightConeItem.getLightCone().get(0).getName() + " = " + neededLC1 + "\n"+
                    "Needed for light cone " + lightConeItem.getLightCone().get(1).getName() + " = " + neededLC2;
        } else if(neededLC1 + neededLC2 > collectedLC) {
            int need = neededLC1 + neededLC2 -  collectedLC;
            return "You need " + need + " " + name + " light cone items" + "\n"+
                    "Needed for light cone " + lightConeItem.getLightCone().get(0).getName() + " = " + neededLC1 + "\n"+
                    "Needed for light cone " + lightConeItem.getLightCone().get(1).getName() + " = " + neededLC2;
        } else {
            throw new LightConeItemNotFoundException("Item with name " + name + " not found");
        }
    }

    public LightConeItem update(Long id, LightConeItem lightConeItem) throws LightConeItemNotFoundException {
        LightConeItem lightConeItemUpdated = getLightConeItemsById(id);
        lightConeItemUpdated.setName(lightConeItem.getName());
        lightConeItemUpdated.setCollected(lightConeItem.getCollected());
        lightConeItemUpdated.setLightCone(lightConeItem.getLightCone());
        return lightConeItemRepository.save(lightConeItemUpdated);
    }

    public void delete(Long id) throws LightConeItemNotFoundException {
        LightConeItem lightConeItem = getLightConeItemsById(id);
        lightConeItemRepository.delete(lightConeItem);
    }




























}
