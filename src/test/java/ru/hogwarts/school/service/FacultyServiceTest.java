package ru.hogwarts.school.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hogwarts.school.model.Faculty;

import java.util.List;

public class FacultyServiceTest {

    private final FacultyService facultyService =new FacultyService();
    @BeforeEach
    public void beforeEach(){
        facultyService.createFaculty(new Faculty(1L, "A", "red"));
        facultyService.createFaculty(new Faculty(2l, "D", "black"));
        facultyService.createFaculty(new Faculty(3L, "X","green"));

    }
    @Test
    public  void createFaculty(){
        int size = facultyService.getAll().size();
        Faculty actual = new Faculty(4l, "G", "pink");
        Assertions.assertEquals(actual, facultyService.createFaculty(actual));
        Assertions.assertEquals(size+1, facultyService.getAll().size());
    }

    @Test
    public void readFaculty(){
        Assertions.assertEquals(new Faculty(3L, "X", "green"),facultyService.readFaculty(3));
    }

    @Test
    public void updateFacultyNegative(){
        int size = facultyService.getAll().size();
        Faculty actual  =new Faculty(5L, "X", "green");
        Assertions.assertNull(facultyService.updateFaculty(actual));
        Assertions.assertEquals(size, facultyService.getAll().size());
    }
    @Test
    public void updateFaculty(){
        int size = facultyService.getAll().size();
        Faculty actual  =new Faculty(3L, "X", "green");
        Assertions.assertEquals(actual, facultyService.updateFaculty(actual));
        Assertions.assertEquals(size, facultyService.getAll().size());
    }
    @Test
    public  void deleteFaculty(){
        int size  = facultyService.getAll().size();
        Faculty actual  = new Faculty(3L, "X", "green");
        Assertions.assertEquals(actual, facultyService.deleteFaculty(3L));
        Assertions.assertEquals(size-1,facultyService.getAll().size());

    }
    @Test
    public  void endPointFaculty(){
        Faculty actual  = new Faculty(4L, "B", "green");
        facultyService.createFaculty(actual);
        List<Faculty> list = List.of(new Faculty(3L,"X","green"),actual);
        Assertions.assertIterableEquals(list,facultyService.endPointFaculty("green"));
    }

}
