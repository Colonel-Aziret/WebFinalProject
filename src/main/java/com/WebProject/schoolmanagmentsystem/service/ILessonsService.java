package com.WebProject.schoolmanagmentsystem.service;

import com.WebProject.schoolmanagmentsystem.model.LessonsModel;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public interface ILessonsService   {
    List<LessonsModel> getAllLessonss();
    LessonsModel saveLesson(LessonsModel lessonsModel);
    LessonsModel getLessonById(Long id);
    LessonsModel updateLesson(LessonsModel lessonsModel);
    void deleteLessonById(Long id);
}
