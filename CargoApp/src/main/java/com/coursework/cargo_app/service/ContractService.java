package com.coursework.cargo_app.service;

import com.coursework.cargo_app.entity.Contract;
import com.coursework.cargo_app.repo.ContractRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContractService {
    @Autowired
    private ContractRepo contractRepo;

    public void create(Contract country){
        contractRepo.save(country);
    }

    public void update(Contract country) { contractRepo.save(country); }

    public void delete(Contract country) { contractRepo.delete(country); }

    public List<Contract> findAll(){
        return contractRepo.findAll();
    }

    public Optional<Contract> find(Long id){
        return contractRepo.findById(id);
    }
}
