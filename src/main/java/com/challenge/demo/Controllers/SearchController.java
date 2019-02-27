package com.challenge.demo.Controllers;

import com.challenge.demo.Interfaces.StudentsRepository;
import com.challenge.demo.Models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class SearchController {

    @Autowired
    StudentsRepository studentsRepository;

    @GetMapping("/multisearch")
    public String search(Model model , @RequestParam(value = "byname" , required = false) String byname , @RequestParam(value = "StudentID" , defaultValue = "0") int StudentID,
                                       @RequestParam(value = "GradeLevel"  ,defaultValue = "0") int GradeLevel ,@RequestParam( value = "EntryDate" ,defaultValue = "0-00-0000") String EntryDate,
                                       @RequestParam(value = "Campus"  ,defaultValue = "0") int Campus ,@RequestParam(value = "SchoolYr"  ,defaultValue = "0") int SchoolYr
    ) throws ParseException {

        ArrayList<Integer> studentsIds = new ArrayList<>();

        SimpleDateFormat date = new SimpleDateFormat("MM-dd-yyyy");
        Date searchDate = date.parse(EntryDate);

        int month = searchDate.getMonth()+1;
        int day =searchDate.getDate() ;
        int year = searchDate.getYear() +1900;
//
        String matchSyntaxString =  month + "-" + day + "-" + year;



        if (studentsRepository.findByName(byname) != null){


                studentsIds.add(studentsRepository.findByName(byname).getId());



        }
        if ( studentsRepository.findBystudentID(StudentID) != null){
            for (int i = 0; i < studentsRepository.findBystudentID(StudentID).size(); i++) {

                if (!studentsIds.contains(studentsRepository.findBystudentID(StudentID).get(i).getId())) {   //if it didn't added the id to studentsIds arrayList yet
                    studentsIds.add(studentsRepository.findBystudentID(StudentID).get(i).getId());
                }
            }
        }
        if (studentsRepository.findBygradeLevel(GradeLevel) != null){
            for (int i = 0; i < studentsRepository.findBygradeLevel(GradeLevel).size(); i++) {


                    if (!studentsIds.contains(studentsRepository.findBygradeLevel(GradeLevel).get(i).getId())) {   //if it didn't added the id to studentsIds arrayList yet
                    studentsIds.add(studentsRepository.findBygradeLevel(GradeLevel).get(i).getId());
                }
            }
        }
        if (studentsRepository.findBySchoolYr(SchoolYr) != null){
            for (int i = 0; i < studentsRepository.findBySchoolYr(SchoolYr).size(); i++) {

                if (!studentsIds.contains(studentsRepository.findBySchoolYr(SchoolYr).get(i).getId())) {   //if it didn't added the id to studentsIds arrayList yet
                    studentsIds.add(studentsRepository.findBySchoolYr(SchoolYr).get(i).getId());
                }
            }
        }
        if (studentsRepository.findBycampus(Campus) != null){
            for (int i = 0; i < studentsRepository.findBycampus(Campus).size(); i++) {

                if (!studentsIds.contains(studentsRepository.findBycampus(Campus).get(i).getId())) {   //if it didn't added the id to studentsIds arrayList yet
                    studentsIds.add(studentsRepository.findBycampus(Campus).get(i).getId());
                }
            }
        }
        List<Student> students = (List<Student>) studentsRepository.findAll();

//        for (int i = 0; i < studentsRepository.findAll().; i++) {
            for (Student s : students) {
                int sday = studentsRepository.findOne(s.getId()).getEntryDate().getDate();
                int smonth = studentsRepository.findOne(s.getId()).getEntryDate().getMonth() +1;
                int syear = studentsRepository.findOne(s.getId()).getEntryDate().getYear()+1900;
                String sDateFromated = smonth +"-"+sday+"-"+syear;
                model.addAttribute("date" , sDateFromated);

                if (sDateFromated.equals(matchSyntaxString)) {
                    if (!studentsIds.contains(s.getId())) {   //if it didn't added the id to studentsIds arrayList yet
                        studentsIds.add(s.getId());
                    }
                }
//            }
        }


        List<Student> studentsFound = new ArrayList<>() ;
        for(Integer si : studentsIds){
            studentsFound.add(studentsRepository.findOne(si));
        }
        model.addAttribute("students", studentsFound );


        return "search";
    }


    @GetMapping("/onesearch")
    public String onesearch(Model model , @RequestParam String searchInput , @RequestParam Optional<String> checkbox) throws ParseException {
        ArrayList<Integer> studentsIds = new ArrayList<>();

        String[] splited = searchInput.trim().split("\\s+");




            for (String searchString : splited) {
                    List<Student> students = (List<Student>) studentsRepository.findAll();
                            for (Student s : students) {
                                String dateString = s.getEntryDate().toString();
                                if (dateString.equals(searchString)) {
                                    if (!studentsIds.contains(s)) {      //if it didn't added the id to studentsIds arrayList yet
                                        studentsIds.add(s.getId());
                                    }
                                }
                            }
                    if (searchString.matches(".*\\d+.*")) { //if has digit


                        int num = Integer.parseInt(searchString);   //in case the searchString is num


                        if (studentsRepository.findBycampus(num) != null) { //if it match the campus
                            for (int i = 0; i < studentsRepository.findBycampus(num).size(); i++) {
                                if (!studentsIds.contains(studentsRepository.findBycampus(num).get(i).getId())) { //if it didn't added the id to studentsIds arrayList yet

                                    studentsIds.add(studentsRepository.findBycampus(num).get(i).getId());

                                }
                            }
                        }
                        if (studentsRepository.findBystudentID(num) != null) {   //if it match the studentId
                            for (int i = 0; i < studentsRepository.findBystudentID(num).size(); i++) {

                                if (!studentsIds.contains(studentsRepository.findBystudentID(num).get(i).getId())) {    //if it didn't added the id to studentsIds arrayList yet

                                    studentsIds.add(this.studentsRepository.findBystudentID(num).get(i).getId());

                                }
                            }
                        }
                        if (studentsRepository.findBygradeLevel(num) != null) {    //if it match the grade

                            for (int i = 0; i < studentsRepository.findBygradeLevel(num).size(); i++) {

                                if (!studentsIds.contains(studentsRepository.findBygradeLevel(num).get(i).getId())) {   //if it didn't added the id to studentsIds arrayList yet
                                    studentsIds.add(this.studentsRepository.findBygradeLevel(num).get(i).getId());
                                }
                            }
                        }
                        if (studentsRepository.findBySchoolYr(num) != null) {     //if it match the schoolYr
                            for (int i = 0; i < studentsRepository.findBySchoolYr(num).size(); i++) {

                                if (!studentsIds.contains(studentsRepository.findBySchoolYr(num).get(i).getId())) {     //if it didn't added the id to studentsIds arrayList yet

                                    studentsIds.add(this.studentsRepository.findBySchoolYr(num).get(i).getId());

                                }
                            }
                        }
                    } else {
                        //if it doesn't have digit
                        if (studentsRepository.findByNameContaining(searchString) != null) {    //if it match the name
                            for (int i = 0; i < studentsRepository.findByNameContaining(searchString).size(); i++) {
                                if (!studentsIds.contains(studentsRepository.findByNameContaining(searchString).get(i).getId())) {      //if it didn't added the id to studentsIds arrayList yet

                                    studentsIds.add(this.studentsRepository.findByNameContaining(searchString).get(i).getId());
                                }
                            }

                        }

                    }

            }





        List<Student> students = new ArrayList<>() ;
        for(Integer si : studentsIds){
            students.add(studentsRepository.findOne(si));
        }




        if (checkbox.isPresent() && students.size()>0){

            for (String searchString : splited) {
            Student student = students.get(0);
            int num = 0;
                if (searchString.matches(".*\\d+.*")) {
                     num = Integer.parseInt(searchString);
                }
                for (String searchStringTwo : splited) {
                    if (searchStringTwo.matches(".*\\d+.*")) {
                        int num2 = Integer.parseInt(searchStringTwo);
                        if (
                                student.getCampus() == num2 && student.getName().contains(searchString) ||
                                student.getCampus() == num && student.getName().contains(searchStringTwo) ||
                                student.getStudentID() == num2 && student.getName().contains(searchString) ||
                                student.getStudentID() == num && student.getName().contains(searchStringTwo) ||
                                student.getSchoolYr() == num2 && student.getName().contains(searchString) ||
                                student.getSchoolYr() == num && student.getName().contains(searchStringTwo) ||
                                student.getGradeLevel() == num2 && student.getName().contains(searchString) ||
                                student.getGradeLevel() == num && student.getName().contains(searchStringTwo)

                        ){
                            model.addAttribute("students" , student);
                        }
                    }


                }
            }

        }else {
            model.addAttribute("students" , students);

        }



        return "search";
    }
}
