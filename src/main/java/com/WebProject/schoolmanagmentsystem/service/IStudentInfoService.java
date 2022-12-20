package com.WebProject.schoolmanagmentsystem.service;

import com.WebProject.schoolmanagmentsystem.model.StudentInfoModel;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public interface IStudentInfoService   {
    List<StudentInfoModel> getAllStudentInfo();
    StudentInfoModel saveStudentInfo(StudentInfoModel   infoModel);
    StudentInfoModel getStudentInfoById(Long id);
    StudentInfoModel updateStudentInfo(StudentInfoModel  infoModel  );
    void deleteStudentInfoById(Long id);
    List<StudentInfoModel> getStudentInfoByStudentId(Long student_id);
}
