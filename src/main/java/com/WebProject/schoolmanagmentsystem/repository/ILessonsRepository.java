package com.WebProject.schoolmanagmentsystem.repository;

import com.WebProject.schoolmanagmentsystem.model.LessonsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILessonsRepository extends JpaRepository<LessonsModel,Long> {
}
