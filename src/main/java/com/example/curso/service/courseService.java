package com.example.curso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.curso.model.course;
import com.example.curso.repository.courseRepository;

@Service 
public class courseService {
    private final courseRepository repo;

    public courseService(courseRepository repo){
        this.repo = repo;
    }

    public List<course> findAll(){
        return repo.findAll();
    }

    public Optional<course> findById(Long id){
        return repo.findById(id);
    }

    public course save(course course){
        return repo.save(course);
    }

    public Optional<course> update(Long id, course newCourse){
        Optional<course> optional = repo.findById(id);
        course courseBd = optional.get();
        courseBd.setName(newCourse.getName());
        courseBd.setHours(newCourse.getHours());
        repo.save(courseBd);
        return Optional.of(save(courseBd));
    }

    public boolean delete(Long id){
        if (!repo.existsById(id)){
            return false;
        }
        repo.deleteById(id);
        return true;
    }
}
