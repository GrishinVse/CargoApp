package com.coursework.cargo_app.controller;

import com.coursework.cargo_app.entity.Contract;
import com.coursework.cargo_app.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ContractController {
    private final ContractService contractService;

    @Autowired
    public ContractController(ContractService contractService){ this.contractService = contractService; }

    @PostMapping(value = "/cargo_app/contracts")
    public ResponseEntity<?> create(@RequestBody Contract contract){
        contractService.create(contract);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/cargo_app/contracts")
    public ResponseEntity<List<Contract>> findAll(){
        final List<Contract> contractList = contractService.findAll();

        return contractList != null && !contractList.isEmpty()
                ? new ResponseEntity<>(contractList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/cargo_app/contracts/{id}")
    public ResponseEntity<Optional<Contract>> find(@PathVariable(name="id") Long id){
        final Optional<Contract> contract = contractService.find(id);

        return contract != null
                ? new ResponseEntity<>(contract, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/cargo_app/contracts/{id}")
    public ResponseEntity<?> updateContract(@PathVariable(name = "id") Long id, @RequestBody Contract updateContract) {
        return contractService.find(id).map(contract -> {
            contract.setCreation_date(updateContract.getCreation_date());
            contract.setDetails(updateContract.getDetails());
            contract.setPeriod(updateContract.getPeriod());

            contract.setCorporate(updateContract.getCorporate());

            contractService.update(contract);
            return new ResponseEntity<>(contract, HttpStatus.OK);
        }).orElseThrow(() -> new IllegalArgumentException());
    }

    @DeleteMapping(value = "/cargo_app/contracts/{id}")
    public ResponseEntity<?> deleteContract(@PathVariable(name = "id") Long id) {
        return contractService.find(id).map(contract -> {
            contractService.delete(contract);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new IllegalArgumentException());
    }
}
