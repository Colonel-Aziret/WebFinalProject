package com.WebProject.schoolmanagmentsystem.service;

import com.WebProject.schoolmanagmentsystem.model.StudentsModel;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface IStudentsService extends UserDetailsService {

    StudentsModel save(StudentsModel registrationDto);
    List<StudentsModel> getAllStudents();
    StudentsModel saveStudent(StudentsModel   studentsModel);
    StudentsModel getStudentById(Long id);
    StudentsModel updateStudent(StudentsModel studentsModel);
    void deleteStudentById(Long id);
}
