package com.WebProject.schoolmanagmentsystem.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "students")
public class StudentsModel {

    public StudentsModel(String password, Long tcNumber, String name, String surName, Long schoolNumber, Long schoolClass, Long phoneNumber, String mail, boolean gender, Date recordTime) {
        this.tcNumber = tcNumber;
        this.name = name;
        this.surName = surName;
        this.mail = mail;
        this.password = password;
        this.schoolNumber = schoolNumber;
        this.schoolClass = schoolClass;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.recordTime = recordTime;
    }

    public StudentsModel() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "tcNumber")
    private Long tcNumber;

    @Column(name = "name")
    private String name;

    @Column(name = "surName")
    private String surName;

    @Column(name = "schoolNumber")
    private Long schoolNumber;

    @Column(name = "schoolClass")
    private Long schoolClass;

    @Column(name = "phoneNumber")
    private Long phoneNumber;

    @Column(name = "mail")
    private String mail;

    @Column(name = "gender")
    private boolean gender;

    @Column(name = "password")
    private String password;

    @Column(name = "record_time")
    @Temporal(TemporalType.DATE)
    private Date recordTime;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "manager_model_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private ManagerModel managerModel;


    @OneToMany(mappedBy = "studentsModel", cascade = CascadeType.ALL)
    private Set<StudentInfoModel>    studentInfoModels = new HashSet<>();
    public Set<StudentInfoModel> getStudentInfoModel() {
        return studentInfoModels;
    }
    public void setStudentInfoModel(Set<StudentInfoModel> studentInfoModels) {
        this.studentInfoModels = studentInfoModels;
        for (StudentInfoModel b : studentInfoModels) {
            b.setStudentsModel(this);
        }
    }


}
