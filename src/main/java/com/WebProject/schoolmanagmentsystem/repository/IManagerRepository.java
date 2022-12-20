package com.WebProject.schoolmanagmentsystem.repository;

import com.WebProject.schoolmanagmentsystem.model.ManagerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IManagerRepository extends JpaRepository<ManagerModel, Long> {
}
