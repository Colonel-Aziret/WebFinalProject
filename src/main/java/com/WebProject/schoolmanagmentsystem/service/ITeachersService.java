package com.WebProject.schoolmanagmentsystem.service;

import com.WebProject.schoolmanagmentsystem.model.TeachersModel;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ITeachersService  {
    List<TeachersModel> getAllTeachers();
    TeachersModel saveTeacher(TeachersModel   teachersModel);
    TeachersModel getTeacherById(Long id);
    TeachersModel updateTeacher(TeachersModel teachersModel);
    void deleteTeacherById(Long id);
}
