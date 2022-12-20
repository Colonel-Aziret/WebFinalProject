package com.WebProject.schoolmanagmentsystem.controller;

import com.WebProject.schoolmanagmentsystem.model.StudentInfoModel;
import com.WebProject.schoolmanagmentsystem.model.StudentsModel;
import com.WebProject.schoolmanagmentsystem.service.IStudentInfoService;
import com.WebProject.schoolmanagmentsystem.service.IStudentsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
@RequestMapping("/student")
public class StudentController {

    IStudentsService studentsService;
    IStudentInfoService studentInfoService;

    public StudentController(IStudentsService studentsService, IStudentInfoService studentInfoService) {
        this.studentsService = studentsService;
        this.studentInfoService = studentInfoService;
    }

    @GetMapping("/getStudentPage")
    public String showStudent(Model model, HttpServletRequest request, HttpServletResponse response) {
        StudentsModel studentSession = (StudentsModel) request.getSession().getAttribute("student");
        if (studentSession != null) {
            String name = studentSession.getName() + "/" + studentSession.getSurName();
            Cookie cookie1 = new Cookie("UserInfo", String.valueOf(name));
            cookie1.setMaxAge(1 * 24 * 60 * 60);
            cookie1.setSecure(false);
            cookie1.setHttpOnly(false);
            response.addCookie(cookie1);

            Cookie cookie2 = new Cookie("Role", "Student");
            cookie2.setMaxAge(1 * 24 * 60 * 60);
            cookie2.setSecure(false);
            cookie2.setHttpOnly(false);
            response.addCookie(cookie2);

            response.setContentType("text/html;charset=UTF-8");
            StudentsModel studentModel = studentsService.getStudentById(studentSession.getStudentId());
            model.addAttribute("student", studentModel);
            return "studentShow";
        } else {
            return "redirect:/login/Authorization";
        }

    }

    @GetMapping("/getStudentInfoPage/{student_id}")
    public String showStudentInfo(@PathVariable("student_id") Long student_id, Model model, HttpServletRequest request) {
        StudentsModel studentSession = (StudentsModel) request.getSession().getAttribute("student");
        if (studentSession != null) {
            if (student_id == studentSession.getStudentId()) {
                StudentsModel studentModel = studentsService.getStudentById(studentSession.getStudentId());
                List<StudentInfoModel> studentInfoModelList = studentInfoService.getStudentInfoByStudentId(studentSession.getStudentId());
                model.addAttribute("studentInfoModelList", studentInfoModelList);
                return "studentInfoShow";
            }
            return "redirect:/student/getStudentPage";
        } else {
            return "redirect:/login/Authorization";
        }
    }
}
