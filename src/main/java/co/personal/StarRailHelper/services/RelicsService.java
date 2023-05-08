package co.personal.StarRailHelper.services;

import co.personal.StarRailHelper.entites.Character;
import co.personal.StarRailHelper.entites.DTO.RelicsDTO;
import co.personal.StarRailHelper.entites.Relics;
import co.personal.StarRailHelper.exceptions.CharacterNotFoundException;
import co.personal.StarRailHelper.exceptions.RelicsNotFoundException;
import co.personal.StarRailHelper.repositories.RelicsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RelicsService {

    @Autowired
    private CharacterService characterService;

    @Autowired
    private RelicsRepository relicsRepository;

    private Relics getRelicsById(Long id) throws RelicsNotFoundException{
        Optional<Relics> optionalRelics = relicsRepository.findById(id);
        if(optionalRelics.isPresent()){
            return optionalRelics.get();
        } else {
            throw new RelicsNotFoundException("Relic with id " + id + " not found");
        }
    }

    public Relics save(RelicsDTO relicsDTO) throws CharacterNotFoundException {

        Character character = characterService.readByName(relicsDTO.getCharacterName());

        Relics relics = new Relics(
                relicsDTO.getName(),
                character
        );
        return relicsRepository.save(relics);
    }

    public Relics read(Long id) throws RelicsNotFoundException {
        return getRelicsById(id);
    }

    public Relics readByName(String name) throws RelicsNotFoundException {
        Optional<Relics> optionalRelics = relicsRepository.findByName(name);
        if(optionalRelics.isPresent()) {
            return optionalRelics.get();
        } else {
            throw new RelicsNotFoundException("Relic with name " + name + " not found");
        }
    }

    public Relics update(Long id, Relics relics) throws RelicsNotFoundException {
        Relics relicsUpdate = getRelicsById(id);
        relicsUpdate.setName(relics.getName());
        relicsUpdate.setEnhancementItems(relics.getEnhancementItems());
        relicsUpdate.setCharacter(relics.getCharacter());
        return relicsRepository.save(relicsUpdate);
    }

    public void delete(Long id) throws RelicsNotFoundException{
        Relics relics = getRelicsById(id);
        relicsRepository.delete(relics);
    }
}
