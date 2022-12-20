package com.WebProject.schoolmanagmentsystem.controller;

import com.WebProject.schoolmanagmentsystem.model.StudentInfoModel;
import com.WebProject.schoolmanagmentsystem.model.StudentsModel;
import com.WebProject.schoolmanagmentsystem.model.TeachersModel;
import com.WebProject.schoolmanagmentsystem.service.ILessonsService;
import com.WebProject.schoolmanagmentsystem.service.IStudentInfoService;
import com.WebProject.schoolmanagmentsystem.service.IStudentsService;
import com.WebProject.schoolmanagmentsystem.service.ITeachersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Controller
@RequestMapping(value = "/teacher")
public class TeacherController {
    private static Long Student_Id;
    ILessonsService lessonsService;
    IStudentInfoService studentInfoService;
    ITeachersService teachersService;
    IStudentsService studentsService;

    public TeacherController(ILessonsService lessonsService, IStudentInfoService studentInfoService, IStudentsService studentsService, ITeachersService teachersService) {
        this.teachersService = teachersService;
        this.studentsService = studentsService;
        this.studentInfoService = studentInfoService;
        this.lessonsService = lessonsService;
    }

    @GetMapping(value = "/getTeacherPage")
    public String getTeacherPage(Model model, HttpServletRequest request, HttpServletResponse response) {
        TeachersModel teachersModel = (TeachersModel) request.getSession().getAttribute("teacher");
        if (teachersModel != null) {

            String name = teachersModel.getName();

            Cookie cookie1 = new Cookie("UserInfo", String.valueOf(name));
            cookie1.setMaxAge(1 * 24 * 60 * 60);
            cookie1.setSecure(false);
            cookie1.setHttpOnly(false);
            response.addCookie(cookie1);


            Cookie cookie2 = new Cookie("Role", "Teacher");
            cookie2.setMaxAge(1 * 24 * 60 * 60);
            cookie2.setSecure(false);
            cookie2.setHttpOnly(false);
            response.addCookie(cookie2);


            List<StudentInfoModel> list = studentInfoService.getAllStudentInfo().stream().filter(x -> x.getTeacherId() == (teachersModel.getTeacherId())).collect(Collectors.toList());

            List<StudentsModel> studentList = new ArrayList<>();
            for (StudentInfoModel studentInfoModel : list) {
                if (teachersModel.getTeacherId() == studentInfoModel.getTeacherId()) {
                    studentList.add(studentsService.getStudentById(studentInfoModel.getStudentsModel().getStudentId()));
                }
            }
            model.addAttribute("studentList", studentList);
            return "teacherShow";
        } else {
            return "redirect:/login/Authorization";
        }

    }

    @GetMapping(value = "/getStudentInfoPage/{student_id}")
    public String getStudentInfoPage(@PathVariable("student_id") Long student_id, Model model, HttpServletRequest request) {
        TeachersModel teachersModel = (TeachersModel) request.getSession().getAttribute("teacher");
        if (teachersModel != null) {
            Student_Id = student_id;
            //bu öğretmene ait öğrenci infosunu aldık
            List<StudentInfoModel> list = studentInfoService.getAllStudentInfo().stream().filter(x -> x.getTeacherId() == (teachersModel.getTeacherId())).collect(Collectors.toList());
            // bu öğretmene ait olan öğrencileri alacağız şimdi
            List<StudentsModel> studentList = new ArrayList<>();
            for (StudentInfoModel studentInfoModel : list) {
                if (teachersModel.getTeacherId() == studentInfoModel.getTeacherId()) {
                    studentList.add(studentsService.getStudentById(studentInfoModel.getStudentsModel().getStudentId()));
                }
            }
            StudentsModel studentModel = studentList.stream().filter(x -> x.getStudentId() == student_id).collect(Collectors.toList()).stream().findFirst().get();

            Stream<StudentInfoModel> infoList = studentInfoService.getStudentInfoByStudentId(studentModel.getStudentId()).stream().filter(x -> x.getTeacherId() == teachersModel.getTeacherId());
            model.addAttribute("infoList", infoList);
            return "teacherStudentInfoShow";
        } else {
            return "redirect:/login/Authorization";
        }
    }


    @PostMapping(value = "/postStudentInfoCreate")
    public String postStudentInfoCreate(@Valid @ModelAttribute("studentInfoModel") StudentInfoModel studentInfoModel, Model model, HttpServletRequest request) {
        try {
            TeachersModel teachersModel = (TeachersModel) request.getSession().getAttribute("teacher");
            if (teachersModel != null) {
                if (studentInfoModel == null) {
                    return "redirect:/teacher/getTeacherPage";
                }
                studentInfoModel.setTeacherId(teachersModel.getTeacherId().intValue());
                studentInfoModel.setLessonName(lessonsService.getLessonById(teachersModel.getLessonsModel().getLessonId()).getLessonName());
                studentInfoModel.setStudentsModel(studentsService.getStudentById(Student_Id));
                studentInfoService.saveStudentInfo(studentInfoModel);
                return "teacherStudentInfoShow";
            } else {
                return "redirect:/login/Authorization";
            }
        } catch (Exception e) {
            return "redirect:/teacher/getTeacherPage";
        }

    }


    @GetMapping(value = "/getStudentInfoUpdate/{student_info_id}")
    public String getStudentInfoUpdate(@PathVariable("student_info_id") Long student_info_id, Model model, HttpServletRequest request) {
        TeachersModel teachersModel = (TeachersModel) request.getSession().getAttribute("teacher");
        if (teachersModel != null) {
            StudentInfoModel studentInfoModel = studentInfoService.getStudentInfoById(student_info_id);
            model.addAttribute("studentInfoModel", studentInfoModel);
            return "teacherStudentInfoUpdate";
        } else {
            return "redirect:/login/Authorization";
        }
    }

    @PostMapping(value = "/postStudentInfoUpdate")
    public String postStudentInfoUpdate(@Valid @ModelAttribute("studentInfoModel") StudentInfoModel studentInfoModel, Model model, HttpServletRequest request) {
        try {
            TeachersModel teachersModel = (TeachersModel) request.getSession().getAttribute("teacher");
            if (teachersModel != null) {
                if (studentInfoModel == null) {
                    return "redirect:/teacher/getTeacherPage";
                }
                studentInfoModel.setStudentsModel(studentsService.getStudentById(Student_Id));
                studentInfoService.saveStudentInfo(studentInfoModel);
                return "redirect:/teacher/getStudentInfoUpdate/" + studentInfoModel.getStudentInfoId();
            } else {
                return "redirect:/login/Authorization";
            }
        } catch (Exception e) {
            return "redirect:/teacher/getTeacherPage";
        }
    }
}
