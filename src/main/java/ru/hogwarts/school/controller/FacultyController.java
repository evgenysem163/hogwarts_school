package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;

@RequestMapping("faculty")
@RestController
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping ("{id}")              // GET http://localhost:8080/student/2
    public ResponseEntity<Faculty> getFaculty(@PathVariable Long id) {
        Faculty faculty = facultyService.readFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PostMapping              // POST http://localhost:8080/student
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @PutMapping//PUT http://localhost:8080/student
    public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty) {
        Faculty faculty1 = facultyService.updateFaculty(faculty);
        if (faculty1 == null) {
            return ResponseEntity.notFound().build();

        }
        return ResponseEntity.ok(faculty1);
    }
    @DeleteMapping("{id}") //DELETE http://localhost:8080/student/2
    public Faculty deleteFaculty(@PathVariable Long id){
        return facultyService.deleteFaculty(id);
    }
    @GetMapping("/color/color")
    public ResponseEntity <Collection <Faculty>> endPointFaculty(@PathVariable String color ){
        Collection<Faculty> faculties = facultyService.endPointFaculty(color);
        if(faculties==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculties);
    }


}
