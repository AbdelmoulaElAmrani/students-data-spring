package com.challenge.demo.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Student {


    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private int schoolYr;

    @Column(nullable = false)
    private int campus;

    @Column(nullable = false)
    private int studentID;

    @Column(nullable = false)
    private Date entryDate;

    @Column(nullable = false)
    private int gradeLevel;

    @Column(nullable = false)
    private String name;

    public Student() {
    }


    public Student(int schoolYr, int campus, int studentID, Date entryDate, int gradeLevel, String name) {
        this.schoolYr = schoolYr;
        this.campus = campus;
        this.studentID = studentID;
        this.entryDate = entryDate;
        this.gradeLevel = gradeLevel;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSchoolYr() {
        return schoolYr;
    }

    public void setSchoolYr(int schoolYr) {
        this.schoolYr = schoolYr;
    }

    public int getCampus() {
        return campus;
    }

    public void setCampus(int campus) {
        this.campus = campus;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public int getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(int gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}