package com.example.curso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.curso.model.teaching;
import com.example.curso.repository.teachingRepository;

@Service 
public class teachingService {
    private final teachingRepository repo;

    public teachingService(teachingRepository repo){
        this.repo = repo;
    }
    
    public List<teaching> findAll(){
        return repo.findAll();
    }

    public Optional<teaching> findById(Long id){
        return repo.findById(id);
    }

    public teaching save(teaching teaching){
        return repo.save(teaching);
    }

    public Optional<teaching> update(Long id, teaching newTeaching){
        Optional<teaching> optional = repo.findById(id);
        teaching teachingBd = optional.get();
        teachingBd.setName(newTeaching.getName());
        teachingBd.setLastname(newTeaching.getLastname());
        teachingBd.setAge(newTeaching.getAge());
        save(teachingBd);
        return Optional.of(save(teachingBd));
    }

    public boolean delete(Long id){
        if(!repo.existsById(id)){
            return false;
        }
        repo.deleteById(id);
        return true;
    }
}
