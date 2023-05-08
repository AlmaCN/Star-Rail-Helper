package co.personal.StarRailHelper.controllers;

import co.personal.StarRailHelper.entites.AscensionItems;
import co.personal.StarRailHelper.entites.DTO.AscensionItemsDTO;
import co.personal.StarRailHelper.exceptions.AscensionItemsNotFoundException;
import co.personal.StarRailHelper.exceptions.CharacterNotFoundException;
import co.personal.StarRailHelper.exceptions.LightConeNotFoundException;
import co.personal.StarRailHelper.services.AscensionItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/as-it")
public class AscensionItemsController {

    @Autowired
    private AscensionItemsService ascensionItemsService;

    @PostMapping("/create-c")
    public ResponseEntity createAscensionItemForCharacter(@RequestBody AscensionItemsDTO ascensionItems){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(ascensionItemsService.saveForCharacter(ascensionItems));
        } catch (CharacterNotFoundException | LightConeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/create-l")
    public ResponseEntity createAscensionItemForLightCone(@RequestBody AscensionItemsDTO ascensionItems){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(ascensionItemsService.saveForLightCone(ascensionItems));
        } catch (CharacterNotFoundException | LightConeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/read-name")
    public ResponseEntity readAscensionItemByName(@RequestParam String name){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ascensionItemsService.readByName(name));
        } catch (AscensionItemsNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/read-one/{id}")
    public ResponseEntity readAscensionItem(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ascensionItemsService.read(id));
        } catch (AscensionItemsNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateAscensionItem(@PathVariable Long id, @RequestBody AscensionItems ascensionItems){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ascensionItemsService.update(id, ascensionItems));
        } catch (AscensionItemsNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteAscensionItem(@PathVariable Long id){
        try {
            ascensionItemsService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Ascension Item deleted");
        } catch (AscensionItemsNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
