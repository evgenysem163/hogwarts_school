package ru.hogwarts.school.controlller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.dto.FacultyDtoOut;
import ru.hogwarts.school.dto.StudentDtoIn;
import ru.hogwarts.school.dto.StudentDtoOut;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public StudentDtoOut create(@RequestBody StudentDtoIn studentDtoIn) {
        return studentService.create(studentDtoIn);
    }

    @PutMapping("/{id}")
    public StudentDtoOut update(@PathVariable("id") long id, @RequestBody StudentDtoIn studentDtoIn) {
        return studentService.update(id, studentDtoIn);
    }

    @GetMapping("/{id}")
    public StudentDtoOut get(@PathVariable("id") long id) {
        return studentService.get(id);
    }

    @DeleteMapping("/{id}")
    public StudentDtoOut delete(@PathVariable("id") long id) {
        return studentService.delete(id);
    }

    @GetMapping
    public List<StudentDtoOut> findAll(@RequestParam(required = false) Integer age) {
        return studentService.findAll(age);
    }

    @GetMapping("/filter")
    public List<StudentDtoOut> findByAgeBetween(@RequestParam int ageFrom, @RequestParam int ageTo) {
        return studentService.findByAgeBetween(ageFrom, ageTo);
    }

    @GetMapping("/{id}/faculty")
    public FacultyDtoOut findFaculty(@PathVariable("id") long id) {
        return studentService.findFaculty(id);
    }


    @GetMapping("/name")
    public List<String> nameToUpperCase(){
        return studentService.nameToUpperCase();
    }

    @GetMapping("/avg-age")
    public double avgAge(){
        return studentService.avgAge();
    }

    @GetMapping("/fast")
        public Integer fast(){
            return studentService.fast();
    }
    @GetMapping("/firstThread")
    public void firstThread(){
        studentService.firstThread();
    }

    @GetMapping("/twoThreadAsync")
    public void twoThreadAsync(){
        studentService.twoThreadAsync();
    }
}


