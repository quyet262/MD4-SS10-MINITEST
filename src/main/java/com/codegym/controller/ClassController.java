package com.codegym.controller;

import com.codegym.model.ClassStudent;
import com.codegym.service.IClassService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/classes")
public class ClassController {
    private final IClassService classService;

    public ClassController(IClassService classService) {
        this.classService = classService;
    }

    @GetMapping("")
    public ModelAndView index(@PageableDefault(value = 5) Pageable pageable) {
        ModelAndView mav = new ModelAndView("/classes/index");
        Page<ClassStudent> classes = classService.findAll(pageable);
        mav.addObject("classes", classes);
        return mav;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable long id) {
        ModelAndView mav = new ModelAndView("/classes/delete");
        ClassStudent classStudent  = classService.findStudentById(id);
        mav.addObject("classStudent", classStudent);
        return mav;
    }
    @PostMapping("/delete")
    public String delete(ClassStudent classStudent) {
        classService.deleteClassByid(classStudent.getId());
        return "redirect:/classes";
    }
}
