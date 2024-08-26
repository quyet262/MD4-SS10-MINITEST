package com.codegym.service.impl;

import com.codegym.model.ClassStudent;
import com.codegym.model.DTO.ClassStudentDTO;
import com.codegym.model.Student;
import com.codegym.repository.IClassRepository;
import com.codegym.service.IClassService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ClassService implements IClassService {
    private final IClassRepository classRepository;

    public ClassService(IClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    @Override
    public Iterable<ClassStudent> findAll() {
        return classRepository.findAll();
    }

    @Override
    public void save(ClassStudent classStudent) {

    }

    @Override
    public Optional<ClassStudent> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public ClassStudent findStudentById(Long id) {
        return classRepository.findById(id).get();
    }

    @Override
    public Page<ClassStudent> findAll(Pageable pageable) {
        return classRepository.findAll(pageable);
    }

    @Override
    public Iterable<ClassStudentDTO> getCountClass() {
        return classRepository.getCountClass();
    }

    @Override
    public void deleteClassByid(Long id) {
        classRepository.deleteClassByid(id);
    }
}
