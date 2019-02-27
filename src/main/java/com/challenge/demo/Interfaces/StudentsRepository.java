package com.challenge.demo.Interfaces;

import com.challenge.demo.Models.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface StudentsRepository extends CrudRepository<Student, Integer> {

//    List<Student> findAllByCampusContainingOrNameContainingOrSchoolYrContaining( Optional<String> campus , Optional<String> name , Optional<String> SchoolYr);


    List<Student> findByNameContaining(String name);
    Student findByName(String name);
    List<Student> findBycampus(int campus);
    List<Student> findBySchoolYr(int schoolYr);
    List<Student> findBystudentID(int studentId);
    List<Student> findByentryDate(Date entryDate);
    List<Student> findBygradeLevel(int gradeLevel);

//


}
