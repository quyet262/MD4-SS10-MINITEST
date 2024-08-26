package com.codegym.service;

import com.codegym.model.ClassStudent;
import com.codegym.model.DTO.ClassStudentDTO;
import com.codegym.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface IClassService extends IGenerateService<ClassStudent> {
    Page<ClassStudent> findAll(Pageable pageable);
    Iterable<ClassStudentDTO> getCountClass();
    void deleteClassByid(@Param("id") Long id);
}
