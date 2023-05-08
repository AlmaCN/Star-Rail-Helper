package co.personal.StarRailHelper.controllers;

import co.personal.StarRailHelper.entites.DTO.TracesItemsDTO;
import co.personal.StarRailHelper.entites.TracesItems;
import co.personal.StarRailHelper.exceptions.TraceNotFoundException;
import co.personal.StarRailHelper.exceptions.TracesItemsNotFoundException;
import co.personal.StarRailHelper.services.TracesItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tr-it")
public class TracesItemsController {

    @Autowired
    private TracesItemsService tracesItemsService;

    @PostMapping("/create")
    public ResponseEntity createTraceItem(@RequestBody TracesItemsDTO tracesItems){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(tracesItemsService.save(tracesItems));
        } catch (TraceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/read/{id}")
    public ResponseEntity readTraceItem(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(tracesItemsService.read(id));
        } catch (TracesItemsNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/read")
    public ResponseEntity readTraceItemByName(@RequestParam String name){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(tracesItemsService.readByName(name));
        } catch (TracesItemsNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateTraceItem(@PathVariable Long id, @RequestBody TracesItems tracesItems){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(tracesItemsService.update(id, tracesItems));
        } catch (TracesItemsNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTraceItem(@PathVariable Long id){
        try {
            tracesItemsService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Trace Items deleted");
        } catch (TracesItemsNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
