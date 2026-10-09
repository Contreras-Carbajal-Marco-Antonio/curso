package com.example.curso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.curso.model.course;

public interface courseRepository extends JpaRepository<course, Long> {

}
