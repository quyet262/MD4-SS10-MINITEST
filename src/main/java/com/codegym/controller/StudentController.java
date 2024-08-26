package com.codegym.controller;

import com.codegym.model.ClassStudent;
import com.codegym.model.DTO.ClassStudentDTO;
import com.codegym.model.Student;
import com.codegym.model.StudentForm;
import com.codegym.service.IClassService;
import com.codegym.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/students")
public class StudentController {
    private final IStudentService studentService;
    private final IClassService classService;

    @Autowired
    public StudentController(IStudentService studentService, IClassService classService) {
        this.studentService = studentService;
        this.classService = classService;
    }



    @ModelAttribute("classes")
    private Iterable<ClassStudent> classStudents() {return classService.findAll();}


    @GetMapping("")
    public ModelAndView index(@PageableDefault(value = 5) Pageable pageable) {
        ModelAndView mav = new ModelAndView("/students/index");
        Page<Student> students = studentService.findAll(pageable);
        Iterable<ClassStudentDTO> classStudentDTOS = classService.getCountClass();
        mav.addObject("students",students);
        mav.addObject("classStudentDTOS",classStudentDTOS);
        return mav;
    }

    @GetMapping("/search")
    public ModelAndView search(@RequestParam("search") Optional<String> search,
                               @PageableDefault(value = 5) Pageable pageable) {
    Page<Student> students;
    if (search.isPresent()) {
        students = studentService.findByLastNameContaining(search.get(), pageable);
    }else {
        students = studentService.findAll(pageable);
    }
    ModelAndView mav = new ModelAndView("/students/index");
    mav.addObject("students",students);
    mav.addObject("search",search.orElse(""));
    return mav;
    }

    @GetMapping("/create")
    public ModelAndView create() {
        ModelAndView mav = new ModelAndView("/students/create");
        mav.addObject("student", new Student());
        return mav;
    }
    @Value("${file-upload}")
    private String upload;
    @PostMapping("save")
    public String save(@ModelAttribute("student") StudentForm studentForm) {
        MultipartFile file = studentForm.getImage();

        String fileName = file.getOriginalFilename();
        try {
            FileCopyUtils.copy(file.getBytes(), new File(upload + fileName));
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

        Student student = new Student();
        student.setImage(fileName);
        student.setFirstName(studentForm.getFirstName());
        student.setLastName(studentForm.getLastName());
        student.setClassStudent(studentForm.getClassStudent());
        student.setAddress(studentForm.getAddress());
        student.setDob(studentForm.getDob());
        student.setMark(studentForm.getMark());
        studentService.save(student);
        return "redirect:/students";
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable long id) {
       Optional<Student> student = studentService.findById(id);
       if (student.isPresent()) {
           ModelAndView mav = new ModelAndView("/students/update");
           mav.addObject("student", student.get());
           return mav;
       }else {
            return new ModelAndView("redirect:/error_404");
       }
    }
    @PostMapping("/update")
    public String update(@ModelAttribute("student") StudentForm studentForm) {
        Student existingStudent = studentService.findStudentById(studentForm.getId());
        MultipartFile file = studentForm.getImage();
        String fileName;
        if (!file.isEmpty()) {
            fileName = file.getOriginalFilename();
            try {
            FileCopyUtils.copy(file.getBytes(), new File(upload+fileName));
            }catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            fileName = existingStudent.getImage();
        }
        existingStudent.setImage(fileName);
        existingStudent.setFirstName(studentForm.getFirstName());
        existingStudent.setLastName(studentForm.getLastName());
        existingStudent.setClassStudent(studentForm.getClassStudent());
        existingStudent.setAddress(studentForm.getAddress());
        existingStudent.setDob(studentForm.getDob());
        existingStudent.setMark(studentForm.getMark());
        studentService.save(existingStudent);
        return "redirect:/students";

    }

    @GetMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable long id) {
        Optional<Student> student = studentService.findById(id);
        if (student.isPresent()) {
            ModelAndView mav = new ModelAndView("/students/delete");
            mav.addObject("student", student.get());
            return mav;
        }else
            return new ModelAndView("redirect:/error_404");
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute("student") Student student) {
        studentService.remove(student.getId());
        return "redirect:/students";
    }

}
