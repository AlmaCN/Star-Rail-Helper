package co.personal.StarRailHelper.controllers;

import co.personal.StarRailHelper.entites.Character;
import co.personal.StarRailHelper.entites.DTO.CharacterDTO;
import co.personal.StarRailHelper.exceptions.CharacterNotFoundException;
import co.personal.StarRailHelper.services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/character")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @PostMapping("/create")
    public ResponseEntity createCharacter(@RequestBody CharacterDTO character){
        return ResponseEntity.status(HttpStatus.CREATED).body(characterService.save(character));
    }

    @GetMapping("/read-id/{id}")
    public ResponseEntity readCharacter(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(characterService.read(id));
        } catch (CharacterNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/read-name")
    public ResponseEntity readCharacterByName(@RequestParam String name){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(characterService.readByName(name));
        } catch (CharacterNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCharacter(@PathVariable Long id, @RequestBody CharacterDTO character){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(characterService.update(id, character));
        } catch (CharacterNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCharacter(@PathVariable Long id){
        try {
            characterService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Character deleted");
        } catch (CharacterNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
