package ru.hogwarts.school.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hogwarts.school.model.Student;

import java.util.List;

public class StudentServiceTest {
   private final StudentService studentService=new StudentService();
   @BeforeEach
   public void beforeEach(){
       studentService.createStudent(new Student(1L, "A", 21));
       studentService.createStudent(new Student(2l, "D", 19));
       studentService.createStudent(new Student(3L, "X",15));

   }
   @Test
   public  void createStudent(){
       int size = studentService.getAll().size();
       Student actual = new Student(4l, "G", 15);
       Assertions.assertEquals(actual, studentService.createStudent(actual));
       Assertions.assertEquals(size+1, studentService.getAll().size());
   }

   @Test
    public void readStudent(){
       Assertions.assertEquals(new Student(3L, "X", 15),studentService.readStudent(3));
   }

   @Test
    public void updateStudentNegative(){
       int size = studentService.getAll().size();
       Student actual  =new Student(5L, "X", 15);
       Assertions.assertNull(studentService.updateStudent(actual));
       Assertions.assertEquals(size, studentService.getAll().size());
   }
   @Test
   public void updateStudent(){
       int size = studentService.getAll().size();
       Student actual  =new Student(3L, "X", 15);
       Assertions.assertEquals(actual, studentService.updateStudent(actual));
       Assertions.assertEquals(size, studentService.getAll().size());
   }
   @Test
    public  void deleteStudent(){
       int size  = studentService.getAll().size();
       Student actual  = new Student(3L, "X", 15);
       Assertions.assertEquals(actual, studentService.deleteStudent(3L));
       Assertions.assertEquals(size-1,studentService.getAll().size());

   }
   @Test
    public  void endPointStudent(){
       Student actual  = new Student(4L, "B", 15);
       studentService.createStudent(actual);
       List<Student> list = List.of(new Student(3L,"X",15),actual);
       Assertions.assertIterableEquals(list,studentService.endPointStudent(15));
   }




}
