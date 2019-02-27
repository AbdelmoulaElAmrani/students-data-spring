package com.challenge.demo.Controllers;

import com.challenge.demo.Interfaces.StudentsRepository;
import com.challenge.demo.Models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class AddController {
    @Autowired
    StudentsRepository studentsRepository;

    @GetMapping("/add")
    public String home(Model model) {
       model.addAttribute("student", new Student());
        return "add-student";
    }

    @PostMapping("/add")
    public String saveUser( @ModelAttribute Student student, @RequestParam( value = "month") String month , @RequestParam(value = "day")String day ,  @RequestParam(value = "year")String year ) throws ParseException {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
//        Date entryDate = dateFormat.parse(EntryDate);
//        int month = entryDate.getMonth()+1;
//        int day =entryDate.getDate() ;
//        int year = entryDate.getYear() +1900;
////
////        String matchSyntaxString =  month + "-" + day + "-" + year;

        try {
            Date date = new SimpleDateFormat("MM-dd-yyyy").parse(month + "-" + day + "-" + year);
            student.setEntryDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        studentsRepository.save(student);
        return "redirect:/";
    }

}
