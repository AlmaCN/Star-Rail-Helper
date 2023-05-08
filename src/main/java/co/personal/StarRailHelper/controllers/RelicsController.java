package co.personal.StarRailHelper.controllers;

import co.personal.StarRailHelper.entites.DTO.RelicsDTO;
import co.personal.StarRailHelper.entites.Relics;
import co.personal.StarRailHelper.exceptions.CharacterNotFoundException;
import co.personal.StarRailHelper.exceptions.RelicsNotFoundException;
import co.personal.StarRailHelper.services.RelicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/relics")
public class RelicsController {

    @Autowired
    private RelicsService relicsService;

    @PostMapping("/create")
    public ResponseEntity createRelic(@RequestBody RelicsDTO relics){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(relicsService.save(relics));
        } catch (CharacterNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/read/{id}")
    public ResponseEntity readRelic(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(relicsService.read(id));
        } catch (RelicsNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/read")
    public ResponseEntity readRelicByName(@RequestParam String name) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(relicsService.readByName(name));
        } catch (RelicsNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateRelic(@PathVariable Long id, @RequestBody Relics relics){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(relicsService.update(id, relics));
        } catch (RelicsNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteRelic(@PathVariable Long id){
        try {
            relicsService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Relic deleted");
        } catch (RelicsNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
