package com.codegym.model;

import javax.persistence.*;

@Entity
@Table(name = "class")
public class ClassStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String className;

    public ClassStudent() {
    }

    public ClassStudent(Long id, String className) {
        this.id = id;
        this.className = className;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
