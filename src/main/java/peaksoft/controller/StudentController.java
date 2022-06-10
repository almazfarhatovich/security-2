package peaksoft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.Search;
import peaksoft.model.Student;
import peaksoft.service.StudentService;

import java.util.List;


@Controller
@RequestMapping("/api/students")

public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // save new student page
    @GetMapping("/show/save/page")
    public String showSaveStudentPage(Model model) {
        model.addAttribute("newStudent", new Student());
        return "savePage";
    }

    // save new student
    @PostMapping("/save")
    public String saveNewStudent(Student student) {
        // save to database
        studentService.save(student);

        return "redirect:/api/students";
    }

    // find all students
    @GetMapping
    public String showAllStudents(@RequestParam(required = false) String search,
                                  Model model) {
        // find all students
        List<Student> studentList = null;
        if (search == null) {
            studentList = studentService.findAll();
        } else {
            studentList = studentService.findAll(search);
        }

        model.addAttribute("studentList", studentList);
        model.addAttribute("search", new Search());

        return "allStudents";
    }

    // find by id
    @GetMapping("/find/{studentId}") // api/students/find/45
    public String findStudentById(@PathVariable Long studentId,
                                  Model model) {
        // find by id
        Student student = studentService.findById(studentId);

        model.addAttribute("student", student);

        return "findById";
    }

    // delete by id
    @GetMapping("/delete/{studentId}")
    public String deleteStudentById(@PathVariable Long studentId) {
        // find all students
        studentService.deleteById(studentId);

        return "allStudents";
    }

    // update
    @GetMapping("/show/update/page/{studentId}")
    public String showUpdateStudentPage(@PathVariable Long studentId,
                                        Model model) {
        // find student by id
        Student student = studentService.findById(studentId);

        model.addAttribute("student", student);

        return "showUpdateStudentPage";
    }

    @PostMapping("/update/{studentId}")
    public String updateStudentPage(@PathVariable Long studentId,
                                    Student newStudent) {
        // update student
        studentService.updateStudent(studentId, newStudent);

        return "redirect:/api/students";
    }
}