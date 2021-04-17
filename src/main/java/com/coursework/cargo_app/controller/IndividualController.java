package com.coursework.cargo_app.controller;

import com.coursework.cargo_app.entity.Individual;
import com.coursework.cargo_app.service.IndividualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class IndividualController {
    private final IndividualService individualService;

    @Autowired
    public IndividualController(IndividualService individualService){ this.individualService = individualService; }

    @PostMapping(value = "/cargo_app/individuals")
    public ResponseEntity<?> create(@RequestBody Individual individual){
        individualService.create(individual);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/cargo_app/individuals")
    public ResponseEntity<List<Individual>> findAll(){
        final List<Individual> individualList = individualService.findAll();

        return individualList != null && !individualList.isEmpty()
                ? new ResponseEntity<>(individualList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/cargo_app/individuals/{id}")
    public ResponseEntity<Optional<Individual>> find(@PathVariable(name="id") Long id){
        final Optional<Individual> individual = individualService.find(id);

        return individual != null
                ? new ResponseEntity<>(individual, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/cargo_app/individuals/{id}")
    public ResponseEntity<?> updateIndividual(@PathVariable(name = "id") Long id, @RequestBody Individual updateIndividual) {
        return individualService.find(id).map(individual -> {
            individual.setEmail(updateIndividual.getEmail());
            individual.setPhone(updateIndividual.getPhone());
            individual.setFirst_name(updateIndividual.getFirst_name());
            individual.setLast_name(updateIndividual.getLast_name());

            individualService.update(individual);
            return new ResponseEntity<>(individual, HttpStatus.OK);
        }).orElseThrow(() -> new IllegalArgumentException());
    }

    @DeleteMapping(value = "/cargo_app/individuals/{id}")
    public ResponseEntity<?> deleteIndividual(@PathVariable(name = "id") Long id) {
        return individualService.find(id).map(individual -> {
            individualService.delete(individual);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new IllegalArgumentException());
    }
}
