package co.personal.StarRailHelper.controllers;

import co.personal.StarRailHelper.entites.DTO.TraceItemDTO;
import co.personal.StarRailHelper.entites.TraceItem;
import co.personal.StarRailHelper.exceptions.TraceNotFoundException;
import co.personal.StarRailHelper.exceptions.TraceItemNotFoundException;
import co.personal.StarRailHelper.services.TraceItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tr-it")
public class TraceItemController {

    @Autowired
    private TraceItemService traceItemService;

    @PostMapping("/create")
    public ResponseEntity createTraceItem(@RequestBody TraceItemDTO tracesItems){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(traceItemService.save(tracesItems));
        } catch (TraceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/read/{id}")
    public ResponseEntity readTraceItem(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(traceItemService.read(id));
        } catch (TraceItemNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/read")
    public ResponseEntity readTraceItemByName(@RequestParam String name){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(traceItemService.readByName(name));
        } catch (TraceItemNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateTraceItem(@PathVariable Long id, @RequestBody TraceItem traceItem){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(traceItemService.update(id, traceItem));
        } catch (TraceItemNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTraceItem(@PathVariable Long id){
        try {
            traceItemService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Trace Items deleted");
        } catch (TraceItemNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
