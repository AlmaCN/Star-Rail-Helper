package co.personal.StarRailHelper.controllers;

import co.personal.StarRailHelper.entites.AscensionItem;
import co.personal.StarRailHelper.entites.DTO.AscensionItemDTO;
import co.personal.StarRailHelper.exceptions.AscensionItemNotFoundException;
import co.personal.StarRailHelper.exceptions.CharacterNotFoundException;
import co.personal.StarRailHelper.exceptions.LightConeNotFoundException;
import co.personal.StarRailHelper.services.AscensionItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/as-it")
public class AscensionItemController {

    @Autowired
    private AscensionItemService ascensionItemService;

    @PostMapping("/create-c-I")
    public ResponseEntity createAscensionItemForCharacter(@RequestBody AscensionItemDTO ascensionItemsDTO){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(ascensionItemService.saveForCharacter(ascensionItemsDTO));
        } catch (CharacterNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/create-c-II")
    public ResponseEntity createAscensionItemForTwoCharacter(@RequestBody AscensionItemDTO ascensionItemsDTO){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(ascensionItemService.saveForTwoCharacter(ascensionItemsDTO));
        } catch (CharacterNotFoundException | LightConeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /*@PostMapping("/create-l")
    public ResponseEntity createAscensionItemForLightCone(@RequestBody AscensionItemDTO ascensionItems){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(ascensionItemService.saveForLightCone(ascensionItems));
        } catch (CharacterNotFoundException | LightConeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }*/

    @GetMapping("/read-name-need")
    public ResponseEntity readAsItNeeded(@RequestParam String name){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ascensionItemService.readByNeeded(name));
        } catch (AscensionItemNotFoundException | CharacterNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/read-name")
    public ResponseEntity readAscensionItemByName(@RequestParam String name){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ascensionItemService.readByName(name));
        } catch (AscensionItemNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/read-one/{id}")
    public ResponseEntity readAscensionItem(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ascensionItemService.read(id));
        } catch (AscensionItemNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateAscensionItem(@PathVariable Long id, @RequestBody AscensionItem ascensionItem){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ascensionItemService.update(id, ascensionItem));
        } catch (AscensionItemNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteAscensionItem(@PathVariable Long id){
        try {
            ascensionItemService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Ascension Item deleted");
        } catch (AscensionItemNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
