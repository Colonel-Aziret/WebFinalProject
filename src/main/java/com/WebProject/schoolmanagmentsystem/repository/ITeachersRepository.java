package com.WebProject.schoolmanagmentsystem.repository;

import com.WebProject.schoolmanagmentsystem.model.TeachersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITeachersRepository extends JpaRepository<TeachersModel, Long> {
}
