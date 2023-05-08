package co.personal.StarRailHelper.services;

import co.personal.StarRailHelper.entites.Character;
import co.personal.StarRailHelper.entites.DTO.LightConeDTO;
import co.personal.StarRailHelper.entites.LightCone;
import co.personal.StarRailHelper.exceptions.CharacterNotFoundException;
import co.personal.StarRailHelper.exceptions.LightConeNotFoundException;
import co.personal.StarRailHelper.repositories.LightConeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LightConeService {

    @Autowired
    private CharacterService characterService;

    @Autowired
    private LightConeRepository lightConeRepository;

    private LightCone getLightConeById(Long id) throws LightConeNotFoundException{
        Optional<LightCone> optionalLightCone = lightConeRepository.findById(id);
        if(optionalLightCone.isPresent()) {
            return optionalLightCone.get();
        } else {
            throw new LightConeNotFoundException("Light Cone with id " + id + " not found");
        }
    }

    public LightCone save(LightConeDTO lightConeDTO) throws CharacterNotFoundException {

        Character character = characterService.readByName(lightConeDTO.getCharacterName());

        LightCone lightCone = new LightCone(
                lightConeDTO.getName(),
                lightConeDTO.getDetails(),
                character);
        return lightConeRepository.save(lightCone);
    }

    public LightCone read(Long id) throws LightConeNotFoundException {
        return getLightConeById(id);
    }

    public LightCone readByName(String name) throws LightConeNotFoundException{
        Optional<LightCone> optionalLightCone = lightConeRepository.findByName(name);
        if(optionalLightCone.isPresent()){
            return optionalLightCone.get();
        } else {
            throw new LightConeNotFoundException("Light Cone with name " + name + " not found");
        }
    }

    public LightCone update(Long id, LightCone lightCone) throws LightConeNotFoundException {
        LightCone lightConeUpdate = getLightConeById(id);
        lightConeUpdate.setName(lightCone.getName());
        lightConeUpdate.setDetails(lightCone.getDetails());
        lightConeUpdate.setAscensionItems(lightCone.getAscensionItems());
        lightConeUpdate.setCharacter(lightCone.getCharacter());
        return lightConeRepository.save(lightConeUpdate);
    }

    public void delete(Long id) throws LightConeNotFoundException{
        LightCone lightCone = getLightConeById(id);
        lightConeRepository.delete(lightCone);
    }
}
