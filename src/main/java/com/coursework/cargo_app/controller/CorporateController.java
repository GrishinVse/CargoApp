package com.coursework.cargo_app.controller;

import com.coursework.cargo_app.entity.Corporate;
import com.coursework.cargo_app.service.CorporateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CorporateController {
    private final CorporateService corporateService;

    @Autowired
    public CorporateController(CorporateService corporateService){ this.corporateService = corporateService; }

    @PostMapping(value = "/cargo_app/corporates")
    public ResponseEntity<?> create(@RequestBody Corporate corporate){
        corporateService.create(corporate);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/cargo_app/corporates")
    public ResponseEntity<List<Corporate>> findAll(){
        final List<Corporate> corporateList = corporateService.findAll();

        return corporateList != null && !corporateList.isEmpty()
                ? new ResponseEntity<>(corporateList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/cargo_app/corporates/{id}")
    public ResponseEntity<Optional<Corporate>> find(@PathVariable(name="id") Long id){
        final Optional<Corporate> corporate = corporateService.find(id);

        return corporate != null
                ? new ResponseEntity<>(corporate, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/cargo_app/corporates/{id}")
    public ResponseEntity<?> updateCorporate(@PathVariable(name = "id") Long id, @RequestBody Corporate updateCorporate) {
        return corporateService.find(id).map(corporate -> {
            corporate.setEmail(updateCorporate.getEmail());
            corporate.setPhone(updateCorporate.getPhone());
            corporate.setCompany_name(updateCorporate.getCompany_name());
            corporate.setLegal_address(updateCorporate.getLegal_address());

            corporateService.update(corporate);
            return new ResponseEntity<>(corporate, HttpStatus.OK);
        }).orElseThrow(() -> new IllegalArgumentException());
    }

    @DeleteMapping(value = "/cargo_app/corporates/{id}")
    public ResponseEntity<?> deleteCorporate(@PathVariable(name = "id") Long id) {
        return corporateService.find(id).map(corporate -> {
            corporateService.delete(corporate);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new IllegalArgumentException());
    }
}
