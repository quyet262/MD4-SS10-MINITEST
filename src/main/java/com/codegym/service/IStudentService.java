package com.codegym.service;

import com.codegym.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IStudentService extends IGenerateService<Student>{

    Page<Student> findAll(Pageable pageable);

    Page<Student> findByLastNameContaining(String lastName, Pageable pageable);

    public Student saveStudent(Student student);
}
