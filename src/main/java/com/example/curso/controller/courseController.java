package com.example.curso.controller;

import com.example.curso.repository.courseRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.curso.model.course;
import com.example.curso.service.courseService;
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
@RequestMapping("/course")
public class courseController {

    private final courseService service;

    public courseController(courseService service, courseRepository courseRepository){
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<course>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<course> fingById(@PathVariable Long id){
        Optional<course> course = service.findById(id);
        if(course.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(course.get());
    }

    @PostMapping()
    public ResponseEntity<course> save(@RequestBody course course) {
        course newCourse = service.save(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCourse);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<course> update(@PathVariable Long id, @RequestBody course course) {
        Optional<course> newCourse = service.update(id, course);
        if (newCourse.isPresent()) {
            return ResponseEntity.ok(newCourse.get());
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<course> delete(@PathVariable Long id){
        boolean deleted = service.delete(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
