package com.codegym.model;

import org.springframework.web.multipart.MultipartFile;

public class StudentForm {
    private Long id;
    private String firstName;
    private String lastName;
    private String dob;
    private String address;
    private Integer mark;
    private MultipartFile image;

    private ClassStudent classStudent;

    public StudentForm() {
    }

    public StudentForm(Long id, String firstName, String lastName, String dob, String address, Integer mark, MultipartFile image, ClassStudent classStudent) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.address = address;
        this.mark = mark;
        this.image = image;
        this.classStudent = classStudent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public ClassStudent getClassStudent() {
        return classStudent;
    }

    public void setClassStudent(ClassStudent classStudent) {
        this.classStudent = classStudent;
    }
}
