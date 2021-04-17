package com.coursework.cargo_app.service;

import com.coursework.cargo_app.entity.Individual;
import com.coursework.cargo_app.repo.IndividualRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IndividualService {
    @Autowired
    private IndividualRepo individualRepo;

    public void create(Individual individual){
        individualRepo.save(individual);
    }

    public void update(Individual individual) { individualRepo.save(individual); }

    public void delete(Individual individual) { individualRepo.delete(individual); }

    public List<Individual> findAll(){
        return individualRepo.findAll();
    }

    public Optional<Individual> find(Long id){
        return individualRepo.findById(id);
    }
}
