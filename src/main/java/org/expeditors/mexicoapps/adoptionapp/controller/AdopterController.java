package org.expeditors.mexicoapps.adoptionapp.controller;

import org.expeditors.mexicoapps.adoptionapp.domain.Adopter;
import org.expeditors.mexicoapps.adoptionapp.service.AdopterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/adopter")
public class AdopterController {

    private final AdopterService adopterService;

    public AdopterController(AdopterService adopterService) {
        this.adopterService = adopterService;
    }

    @GetMapping("test")
    public List<Adopter> get(){
        return adopterService.getAllAdopters();
    }


    @GetMapping
    public ResponseEntity<?> GetAllAdopters(){
        List<Adopter> adopters = adopterService.getAllAdopters();
        if(adopters.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No adopters found");
        }
        return ResponseEntity.ok(adopters);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> GetAdopterById(@PathVariable("id") int id){
        Adopter adopter = adopterService.getAdopter(id);
        if (adopter == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(STR."No adopter with id: \{id}");
        }
        return ResponseEntity.ok(adopter);
    }

    @GetMapping("/byAny/{value}")
    public ResponseEntity<?> GetAdopterByAny(@PathVariable("value") String value){
        List<Adopter> adopters = adopterService.findBy(value.toLowerCase());
        if (adopters == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(STR."No adopter with id: \{value}");
        }
        return ResponseEntity.ok(adopters);
    }

    @PostMapping
    public ResponseEntity<?> addAdopter(@RequestBody Adopter adopter) {
        Adopter newAdopter = adopterService.createAdopter(adopter);

        URI newResource = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(adopter.getId())
                .toUri();

        return ResponseEntity.created(newResource).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAdopter(@PathVariable("id") int id) {
        boolean result = adopterService.deleteAdopter(id);
        if (!result) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(STR."No adopter with id: \{id}");
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<?> updateAdopter(@RequestBody Adopter adopter) {
        Adopter result = adopterService.updateAdopter(adopter);
        if (result == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(STR."No adopter with id: \{adopter.getId()}");
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/adoptPet/{id}/{idPet}")
    public ResponseEntity<?> adoptPet(@PathVariable("id") int id, @PathVariable("idPet") int idPet) {
        boolean result = adopterService.adoptAPet(id,idPet);
        if (!result) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(STR."No adopt a pet");
        }
        return ResponseEntity.noContent().build();
    }
}
