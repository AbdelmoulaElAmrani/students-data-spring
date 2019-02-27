package Services;

import com.challenge.demo.Interfaces.StudentsRepository;
import com.challenge.demo.Models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentsRepository studentsRepository;


//    public List<Student> findBySearchInput(String searchInput){
//        String[] splited = searchInput.trim().split("\\s+");
//        Iterable<Student> allStudents = studentsRepository.findAll();
//        List<Student> students = null;
//        for(Student s : allStudents){
//            for (int i = 0; i < splited.length; i++) {
//                if (splited[i].equals(s.getName())){
//                    students.add((Student) studentsRepository.findByNameContaining(splited[i]));
//                }
//            }
//        }
//
//            return students;
//        }


}
