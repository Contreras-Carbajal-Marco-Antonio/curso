package com.example.curso.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.curso.model.teaching;
import com.example.curso.service.teachingService;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/teaching")
public class teachingController {

    private final teachingService service;

    public teachingController(teachingService service){
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<teaching>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<teaching> findById(@PathVariable Long id) {
        Optional<teaching> teaching = service.findById(id);
        if (teaching.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(teaching.get()); 
    }
    
    @PostMapping()
    public ResponseEntity<teaching> save(@RequestBody teaching teaching) {
        teaching newTeaching = service.save(teaching);
        return  ResponseEntity.status(HttpStatus.CREATED).body(newTeaching);
    }

    @PutMapping("/{id}")
    public ResponseEntity<teaching> update(@PathVariable Long id, @RequestBody teaching teaching) {
        Optional<teaching> newTeaching = service.update(id, teaching);
        if (newTeaching.isPresent()) {
            return ResponseEntity.ok(newTeaching.get());
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<teaching> delete(@PathVariable Long id){
        boolean deleted = service.delete(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return  ResponseEntity.noContent().build();
    }
    
}
