package com.coursework.cargo_app.service;

import com.coursework.cargo_app.entity.Transport;
import com.coursework.cargo_app.repo.TransportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransportService {
    @Autowired
    private TransportRepo transportRepo;

    public void create(Transport transport){
        transportRepo.save(transport);
    }

    public void update(Transport transport) { transportRepo.save(transport); }

    public void delete(Transport transport) { transportRepo.delete(transport); }

    public List<Transport> findAll(){
        return transportRepo.findAll();
    }

    public Optional<Transport> find(Long id){
        return transportRepo.findById(id);
    }
}
