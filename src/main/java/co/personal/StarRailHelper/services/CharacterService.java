package co.personal.StarRailHelper.services;

import co.personal.StarRailHelper.entites.Character;
import co.personal.StarRailHelper.entites.DTO.CharacterDTO;
import co.personal.StarRailHelper.exceptions.CharacterNotFoundException;
import co.personal.StarRailHelper.repositories.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    private Character getCharacterById (Long id) throws CharacterNotFoundException {
        Optional<Character> optionalCharacter = characterRepository.findById(id);
        if(optionalCharacter.isPresent()){
            return optionalCharacter.get();
        } else{
            throw new CharacterNotFoundException("Character with id " + id + " not found");
        }
    }

    public Character save(CharacterDTO characterDTO) {
        Character character = new Character(characterDTO.getName(), characterDTO.getDetails());
        return characterRepository.save(character);
    }

    public Character read(Long id) throws CharacterNotFoundException {
        return getCharacterById(id);
    }

    public Character readByName(String name) throws CharacterNotFoundException {
        Optional<Character> optionalCharacter = characterRepository.findByName(name);
        if (optionalCharacter.isPresent()) {
            return optionalCharacter.get();
        } else {
            throw new CharacterNotFoundException("Character with name " + name + " not found");
        }
    }

    public Character update(Long id, CharacterDTO characterDTO) throws CharacterNotFoundException {
        Character characterToUpdate = getCharacterById(id);
        characterToUpdate.setName(characterDTO.getName());
        characterToUpdate.setDetails(characterDTO.getDetails());
        return characterRepository.save(characterToUpdate);
    }

    public void delete(Long id) throws CharacterNotFoundException {
        Character character = getCharacterById(id);
        characterRepository.delete(character);
    }
}
