package com.challenge.demo.Controllers;

import com.challenge.demo.Interfaces.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.ParseException;

@Controller
public class StudentPageController {
    @Autowired
    StudentsRepository studentsRepository;

    @GetMapping("/student/{id}")
    public String edit(Model model , @PathVariable int id) throws ParseException {
        model.addAttribute("student",studentsRepository.findOne(id));
        model.addAttribute("id" , id);

        String dateString = String.valueOf(studentsRepository.findOne(id).getEntryDate());
        String[] splited = dateString.trim().split("-");
        String[] splitedDate = splited[2].trim().split(" ");


        model.addAttribute("month",splited[1]);
        model.addAttribute("day",splitedDate[0]);
        model.addAttribute("year",splited[0]);

        return "student-page";
    }

}
