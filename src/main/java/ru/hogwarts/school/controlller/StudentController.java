package ru.hogwarts.school.controlller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
        return studentService.createStudent(studentDtoIn);
    }

    @PutMapping("/{id}")
    public StudentDtoOut update(@PathVariable("id") long id, @RequestBody StudentDtoIn studentDtoIn) {
        return studentService.updateStudent(id, studentDtoIn);
    }

    @GetMapping("/{id}")
    public StudentDtoOut get(@PathVariable("id") long id) {
        return studentService.readStudent(id);
    }

    @DeleteMapping("/{id}")
    public StudentDtoOut delete(@PathVariable("id") long id) {
        return studentService.deleteStudent(id);
    }

    @GetMapping
    public List<StudentDtoOut> findAll(@RequestParam(required = false) Integer age) {
        return studentService.endpointStudent(age);
    }

    @GetMapping("/filter")
    public List<StudentDtoOut> findByAgeBetween(@RequestParam int ageFrom, @RequestParam int ageTo) {
        return studentService.findByAgeBetween(ageFrom, ageTo);
    }

    @GetMapping("/{id}/faculty")
    public FacultyDtoOut findFaculty(@PathVariable("id") long id) {

        return studentService.findFaculty(id);
    }

    @PatchMapping(value = "{id}/avatar",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public StudentDtoOut uploadAvatar(@PathVariable long id, @RequestPart ("avatar") MultipartFile multipartFile) {
    return studentService.uploadAvatar(multipartFile, id);

    }
    @GetMapping("/count")
    public int getCountOfStudent(){
        return studentService.getCountOfStudent();
    }
//    @GetMapping("/averageAge")
//    public double getAverageAge(){
//        return studentService.getAverageAge();
//    }
//    @GetMapping("/lastStudent")
//    public List<StudentDtoOut> getLastStudents(@RequestParam(value =
//            "count", defaultValue = "5", required = false) int count){
//        return studentService.getLastStudents(Math.abs(count));

    }

//}


