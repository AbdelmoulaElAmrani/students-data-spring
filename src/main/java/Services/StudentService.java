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



}
