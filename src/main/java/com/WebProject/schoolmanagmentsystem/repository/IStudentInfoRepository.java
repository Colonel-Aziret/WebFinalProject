package com.WebProject.schoolmanagmentsystem.repository;

import com.WebProject.schoolmanagmentsystem.model.StudentInfoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IStudentInfoRepository extends JpaRepository<StudentInfoModel, Long> {

    @Query(value = "SELECT * FROM studentinfo WHERE student_id = ?1", nativeQuery = true)
    public List<StudentInfoModel> findByStudentId(Long studentId);

}
