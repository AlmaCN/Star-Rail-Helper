package co.personal.StarRailHelper.controllers;

import co.personal.StarRailHelper.entites.DTO.TraceDTO;
import co.personal.StarRailHelper.entites.Trace;
import co.personal.StarRailHelper.exceptions.CharacterNotFoundException;
import co.personal.StarRailHelper.exceptions.TraceNotFoundException;
import co.personal.StarRailHelper.services.TraceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trace")
public class TraceController {

    @Autowired
    private TraceService traceService;

    @PostMapping("/create")
    public ResponseEntity createTrace(@RequestBody TraceDTO trace){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(traceService.save(trace));
        } catch (CharacterNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/read/{id}")
    public ResponseEntity readTrace(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(traceService.read(id));
        } catch (TraceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/read")
    public ResponseEntity readTraceByName(@RequestParam String name){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(traceService.readByName(name));
        } catch (TraceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateTrace(@PathVariable Long id, @RequestBody Trace trace){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(traceService.update(id, trace));
        } catch (TraceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTrace(@PathVariable Long id){
        try {
            traceService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Trace deleted");
        } catch (TraceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
