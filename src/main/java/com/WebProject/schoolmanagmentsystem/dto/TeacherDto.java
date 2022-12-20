package com.WebProject.schoolmanagmentsystem.dto;

import com.WebProject.schoolmanagmentsystem.model.LessonsModel;
import com.WebProject.schoolmanagmentsystem.model.TeachersModel;
import lombok.Data;

import java.util.List;

@Data
public class TeacherDto {
    public List<TeachersModel> teachersModels;
    public List<LessonsModel> lessonsModels;
}
