package com.coursework.cargo_app.service;

import com.coursework.cargo_app.entity.Corporate;
import com.coursework.cargo_app.repo.CorporateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CorporateService {
    @Autowired
    private CorporateRepo corporateRepo;

    public void create(Corporate corporate){
        corporateRepo.save(corporate);
    }

    public void update(Corporate corporate) { corporateRepo.save(corporate); }

    public void delete(Corporate corporate) { corporateRepo.delete(corporate); }

    public List<Corporate> findAll(){
        return corporateRepo.findAll();
    }

    public Optional<Corporate> find(Long id){
        return corporateRepo.findById(id);
    }
}
