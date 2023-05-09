package co.personal.StarRailHelper.controllers;

import co.personal.StarRailHelper.entites.DTO.LightConeItemDTO;
import co.personal.StarRailHelper.entites.LightConeItem;
import co.personal.StarRailHelper.exceptions.CharacterNotFoundException;
import co.personal.StarRailHelper.exceptions.LightConeItemNotFoundException;
import co.personal.StarRailHelper.exceptions.LightConeNotFoundException;
import co.personal.StarRailHelper.services.LightConeItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lc-item")
public class LightConeItemController {

    @Autowired
    private LightConeItemService lightConeItemService;

    @PostMapping("/create-I")
    public ResponseEntity createItemForLightCone(@RequestBody LightConeItemDTO lightConeItemDTO){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(lightConeItemService.saveForLightCone(lightConeItemDTO));
        } catch (LightConeNotFoundException | CharacterNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/create-II")
    public ResponseEntity createTwoItemsForLightCone(@RequestBody LightConeItemDTO lightConeItemDTO){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(lightConeItemService.saveForTwoLightCone(lightConeItemDTO));
        } catch (LightConeNotFoundException | CharacterNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/read-name-need")
    public ResponseEntity readItemNeeded(@RequestParam String name){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(lightConeItemService.readByNeeded(name));
        } catch (LightConeNotFoundException | LightConeItemNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/read-name")
    public ResponseEntity readItemByName(@RequestParam String name){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(lightConeItemService.readByName(name));
        } catch (LightConeItemNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/read/{id}")
    public ResponseEntity readLightConeItem(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(lightConeItemService.read(id));
        } catch (LightConeItemNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateLightConeItem(@PathVariable Long id, @RequestBody LightConeItem lightConeItem){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(lightConeItemService.update(id, lightConeItem));
        } catch (LightConeItemNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteLightConeItem(@PathVariable Long id){
        try {
            lightConeItemService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Light Cone Item deleted");
        } catch (LightConeItemNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
