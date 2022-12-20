package com.WebProject.schoolmanagmentsystem.dto;

import com.WebProject.schoolmanagmentsystem.model.StudentInfoModel;
import com.WebProject.schoolmanagmentsystem.model.StudentsModel;
import com.WebProject.schoolmanagmentsystem.model.TeachersModel;
import lombok.Data;

import java.util.List;

@Data
public class StudentInfoDto {
    public List<StudentsModel> studentList;
    public List<TeachersModel> teacherList;
    public List<StudentInfoModel> studentInfoList;
}
