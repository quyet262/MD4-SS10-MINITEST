package com.codegym.repository;

import com.codegym.model.ClassStudent;
import com.codegym.model.DTO.ClassStudentDTO;
import com.codegym.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface IClassRepository extends JpaRepository<ClassStudent, Long> {
    Page<ClassStudent> findAll(Pageable pageable);
@Query(nativeQuery = true, value = "select class.className, count(*) as number from student join class on student.class_id = class.id group by class.className order by number ")
    Iterable<ClassStudentDTO> getCountClass();

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "call delete_class_and_unlink_students(:id) ")
    void deleteClassByid(@Param("id") Long id);
}
