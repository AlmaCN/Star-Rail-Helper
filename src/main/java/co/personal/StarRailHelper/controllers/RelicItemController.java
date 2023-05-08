package co.personal.StarRailHelper.controllers;

import co.personal.StarRailHelper.entites.DTO.RelicItemDTO;
import co.personal.StarRailHelper.entites.RelicItem;
import co.personal.StarRailHelper.exceptions.EnhancementItemsNotFoundException;
import co.personal.StarRailHelper.exceptions.RelicsNotFoundException;
import co.personal.StarRailHelper.services.RelicItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/en-it")
public class RelicItemController {

    @Autowired
    private RelicItemService relicItemService;

    @PostMapping("/create")
    public ResponseEntity createEnhancementItem(@RequestBody RelicItemDTO enhancementItems){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(relicItemService.save(enhancementItems));
        } catch (RelicsNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/read/{id}")
    public ResponseEntity readEnhancementItem(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(relicItemService.read(id));
        } catch (EnhancementItemsNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/read")
    public ResponseEntity readEnhancementItemByName(@RequestParam String name){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(relicItemService.readByName(name));
        } catch (EnhancementItemsNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateEnhancementItem(@PathVariable Long id, @RequestBody RelicItem relicItem){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(relicItemService.update(id, relicItem));
        } catch (EnhancementItemsNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteEnhancementItem(@PathVariable Long id){
        try {
            relicItemService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Enhancement Item deleted");
        } catch (EnhancementItemsNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
