package com.challenge.demo.Controllers;

import com.challenge.demo.Interfaces.StudentsRepository;
import com.challenge.demo.Models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeleteController {
    @Autowired
    StudentsRepository studentsRepository;

    @PostMapping("/delete/{id}")
    public String deleteOrder(@PathVariable int id){
        Student student = studentsRepository.findOne(id);

        studentsRepository.delete(student);
        return "redirect:/";
    }
}
