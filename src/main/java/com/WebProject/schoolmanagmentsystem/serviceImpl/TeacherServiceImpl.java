package com.WebProject.schoolmanagmentsystem.serviceImpl;

import com.WebProject.schoolmanagmentsystem.model.TeachersModel;
import com.WebProject.schoolmanagmentsystem.repository.ITeachersRepository;
import com.WebProject.schoolmanagmentsystem.service.ITeachersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements ITeachersService {
    @Autowired
    private ITeachersRepository teachersRepository;

    public TeacherServiceImpl(ITeachersRepository teachersRepository) {
        this.teachersRepository = teachersRepository;
    }

    @Override
    public List<TeachersModel> getAllTeachers() {
        return teachersRepository.findAll();
    }


    @Override
    public TeachersModel saveTeacher(TeachersModel teachersModel) {
        return teachersRepository.save(teachersModel);
    }

    @Override
    public TeachersModel getTeacherById(Long id) {
        return teachersRepository.getById(id);
    }

    @Override
    public TeachersModel updateTeacher(TeachersModel teachersModel) {
        return teachersRepository.save(teachersModel);
    }

    @Override
    public void deleteTeacherById(Long id) {
        teachersRepository.deleteById(id);
    }
}
