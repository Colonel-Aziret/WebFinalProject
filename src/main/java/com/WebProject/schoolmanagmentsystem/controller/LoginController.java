package com.WebProject.schoolmanagmentsystem.controller;

import com.WebProject.schoolmanagmentsystem.dto.LoginDto;
import com.WebProject.schoolmanagmentsystem.model.ManagerModel;
import com.WebProject.schoolmanagmentsystem.model.StudentsModel;
import com.WebProject.schoolmanagmentsystem.model.TeachersModel;
import com.WebProject.schoolmanagmentsystem.service.IManagerService;
import com.WebProject.schoolmanagmentsystem.service.IStudentsService;
import com.WebProject.schoolmanagmentsystem.service.ITeachersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/login")
public class LoginController {
    IStudentsService studentsService;
    IManagerService managerService;
    ITeachersService teacherService;

    public LoginController(IStudentsService studentsService, IManagerService managerService, ITeachersService teacherService) {
        this.studentsService = studentsService;
        this.managerService = managerService;
        this.teacherService = teacherService;
    }

    @GetMapping(value = "/getLogin")
    public String login(Model model, @ModelAttribute("login") LoginDto loginDto) {
        model.addAttribute("login", loginDto);
        return "login";
    }


    @PostMapping(value = "/postlogin")
    public String login(@ModelAttribute LoginDto loginDto, HttpSession session, HttpServletResponse response) {


        if (loginDto.getRole().equals("student")) {
            List<StudentsModel> studentList = studentsService.getAllStudents();
            for (StudentsModel studentsModel : studentList) {
                if (studentsModel.getTcNumber().equals(loginDto.getTcNo()) && studentsModel.getPassword().equals(loginDto.getPassword())) {
                    session.setAttribute("student", studentsModel);
                    return "redirect:/student/getStudentPage";
                }
            }
        } else if (loginDto.getRole().equals("teacher")) {
            List<TeachersModel> teacherList = teacherService.getAllTeachers();
            for (TeachersModel teachersModel : teacherList) {
                if (teachersModel.getTcNumber().equals(loginDto.getTcNo()) && teachersModel.getPassword().equals(loginDto.getPassword())) {
                    session.setAttribute("teacher", teachersModel);
                    return "redirect:/teacher/getTeacherPage";
                }
            }
        } else if (loginDto.getRole().equals("admin")) {
            List<ManagerModel> managerList = managerService.getAllManager();
            for (ManagerModel managerModel : managerList) {
                if (managerModel.getTcNumber().equals(loginDto.getTcNo()) && managerModel.getManagerPassword().equals(loginDto.getPassword())) {
                    session.setAttribute("admin", managerModel);
                    return "redirect:/management/getManagemetPage";
                }
            }
        }
        return "redirect:/login/getLogin";
    }

    @GetMapping(value = "/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login/getLogin";
    }

    @GetMapping(value = "/Authorization")
    public String getAuthErrorPage() {
        return "authErrorPage";
    }
}
