package com.challenge.demo.Controllers;

import com.challenge.demo.Interfaces.StudentsRepository;
import com.challenge.demo.Models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class EditController {
    @Autowired
    StudentsRepository studentsRepository;

    @GetMapping("/edit/{id}")
    public String edit(Model model , @PathVariable int id) throws ParseException {
        model.addAttribute("student",studentsRepository.findOne(id));
        model.addAttribute("id" , id);

        String dateString = String.valueOf(studentsRepository.findOne(id).getEntryDate());
        String[] splited = dateString.trim().split("-");
        String[] splitedDate = splited[2].trim().split(" ");


        model.addAttribute("month",splited[1]);
        model.addAttribute("day",splitedDate[0]);
        model.addAttribute("year",splited[0]);

        return "edit-student";
    }

    @PostMapping("/edit")
    public String saveUser(@ModelAttribute Student student, @RequestParam( value = "month") String month , @RequestParam(value = "day")String day , @RequestParam(value = "year")String year ,
    @RequestParam( value = "name") String name ,@RequestParam( value = "studentID") int studentID ,@RequestParam( value = "gradeLevel") int gradeLevel ,
    @RequestParam( value = "campus") int campus,@RequestParam( value = "id") int id,
    @RequestParam( value = "schoolYr") int schoolYr) throws ParseException {

        Student thisStudent = studentsRepository.findOne(id);
        thisStudent.setName(name);
        thisStudent.setStudentID(studentID);
        thisStudent.setGradeLevel(gradeLevel);
        thisStudent.setCampus(campus);
        thisStudent.setSchoolYr(schoolYr);
        try {
            Date date = new SimpleDateFormat("MM-dd-yyyy").parse(month + "-" + day + "-" + year);
            thisStudent.setEntryDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        studentsRepository.save(thisStudent);
        return "redirect:/";
    }


}
