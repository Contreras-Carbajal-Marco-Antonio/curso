package com.example.curso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.curso.model.teaching;

public interface teachingRepository extends JpaRepository<teaching, Long> {

}
