package com.codegym.repository;

import com.codegym.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IStudentRepository extends JpaRepository<Student, Long> {

    Page<Student> findAll(Pageable pageable);

    Page<Student> findByLastNameContaining(String lastName, Pageable pageable);

}
