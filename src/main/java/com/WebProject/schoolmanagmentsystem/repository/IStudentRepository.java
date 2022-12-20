package com.WebProject.schoolmanagmentsystem.repository;

import com.WebProject.schoolmanagmentsystem.model.StudentsModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentRepository extends JpaRepository<StudentsModel,Long> {

}
