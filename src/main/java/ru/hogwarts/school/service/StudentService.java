package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service

public class StudentService {
    Map<Long, Student> longStudentMap = new HashMap<>();
    private long counter = 0;
    //crud
  public Student createStudent (Student student){
      student.setId(++counter);
      longStudentMap.put(counter, student);
      return student;
  }

  public Student readStudent (long id){
      return longStudentMap.get(id);
  }
  public Student updateStudent(Student student){
      if (longStudentMap.containsKey(student.getId())){
          longStudentMap.put(student.getId(), student);
          return student;
      }
      return null;
  }
  public Student deleteStudent(long id){
      return longStudentMap.remove(id);
  }
  public Collection<Student> endPointStudent(int age){
      return longStudentMap.values().stream().filter(student ->
              student.getAge()==age).collect(Collectors.toList());
  }

  public  Collection<Student> getAll(){
      return longStudentMap.values();
  }

}

