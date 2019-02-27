package com.challenge.demo.Controllers;

import com.challenge.demo.Interfaces.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    StudentsRepository studentsRepository;

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("students" , studentsRepository.findAll());
        return "index";
    }
}

