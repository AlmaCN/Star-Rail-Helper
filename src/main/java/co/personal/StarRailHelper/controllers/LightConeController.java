package co.personal.StarRailHelper.controllers;

import co.personal.StarRailHelper.entites.DTO.LightConeDTO;
import co.personal.StarRailHelper.entites.LightCone;
import co.personal.StarRailHelper.exceptions.CharacterNotFoundException;
import co.personal.StarRailHelper.exceptions.LightConeNotFoundException;
import co.personal.StarRailHelper.services.LightConeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/light-cone")
public class LightConeController {

    @Autowired
    private LightConeService lightConeService;

    @PostMapping("/create")
    public ResponseEntity createLightCone(@RequestBody LightConeDTO lightCone){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(lightConeService.save(lightCone));
        } catch (CharacterNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/read-id/{id}")
    public ResponseEntity readLightCone(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(lightConeService.read(id));
        } catch (LightConeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/read-name")
    public ResponseEntity readLightConeByName(@RequestParam String name){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(lightConeService.readByName(name));
        } catch (LightConeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateLightCone(@PathVariable Long id, @RequestBody LightCone lightCone){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(lightConeService.update(id, lightCone));
        } catch (LightConeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteLightCone(@PathVariable Long id){
        try {
            lightConeService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Light Cone deleted");
        } catch (LightConeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
