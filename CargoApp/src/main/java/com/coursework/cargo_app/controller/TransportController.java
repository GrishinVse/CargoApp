package com.coursework.cargo_app.controller;

import com.coursework.cargo_app.entity.Transport;
import com.coursework.cargo_app.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TransportController {
    private final TransportService transportService;

    @Autowired
    public TransportController(TransportService transportService){ this.transportService = transportService; }

    @PostMapping(value = "/cargo_app/transports")
    public ResponseEntity<?> create(@RequestBody Transport transport){
        transportService.create(transport);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/cargo_app/transports")
    public ResponseEntity<List<Transport>> findAll(){
        final List<Transport> transportList = transportService.findAll();

        return transportList != null && !transportList.isEmpty()
                ? new ResponseEntity<>(transportList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/cargo_app/transports/{id}")
    public ResponseEntity<Optional<Transport>> find(@PathVariable(name="id") Long id){
        final Optional<Transport> transport = transportService.find(id);

        return transport != null
                ? new ResponseEntity<>(transport, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/cargo_app/transports/{id}")
    public ResponseEntity<?> updateTransport(@PathVariable(name = "id") Long id, @RequestBody Transport updateTransport) {
        return transportService.find(id).map(transport -> {
            transport.setBrand(updateTransport.getBrand());
            transport.setCapacity(updateTransport.getCapacity());
            transport.setCarrying(updateTransport.getCarrying());
            transport.setLicence_plate(updateTransport.getLicence_plate());

            transportService.update(transport);
            return new ResponseEntity<>(transport, HttpStatus.OK);
        }).orElseThrow(() -> new IllegalArgumentException());
    }

    @DeleteMapping(value = "/cargo_app/transports/{id}")
    public ResponseEntity<?> deleteTransport(@PathVariable(name = "id") Long id) {
        return transportService.find(id).map(transport -> {
            transportService.delete(transport);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new IllegalArgumentException());
    }
}
