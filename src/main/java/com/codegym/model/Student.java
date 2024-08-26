package com.codegym.model;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String dob;
    private String address;
    private Integer mark;
    private String image;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClassStudent classStudent;

    public Student() {
    }

    public Student(Long id, String firstName, String lastName, String dob, String address, Integer mark, String image, ClassStudent classStudent) {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ClassStudent getClassStudent() {
        return classStudent;
    }

    public void setClassStudent(ClassStudent classStudent) {
        this.classStudent = classStudent;
    }
}
