package ru.hogwarts.school.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.dto.StudentDtoOut;
import ru.hogwarts.school.entity.Student;

import java.awt.print.Pageable;
import java.util.List;


@Repository

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAllByAge(int age);

    List<Student> findAllByAgeBetween(int ageFrom, int ageTo);

    List<Student> findAllByFaculty_Id(long facultyId);
@Query("SELECT  count (s) FROM Student  s ")
    int getCountOfStudents();

@Query("SELECT  avg (s.age) FROM  Student s ")
    double getAverageAge();
@Query("SELECT  s FROM  Student  s ORDER BY s.id DESC ")
List<Student>getLastStudents(Pageable pageable);
//@Query("SELECT new  ru.hogwarts.school.dto.StudentDtoOut(s.id,s.name, s.age, f.id, f.name, f.color, a.id) FROM  Student  s LEFT  JOIN  Faculty  f ON  s.faculty = f JOIN  Avatar a ON a.student = s  ORDER BY s.id DESC ")
//    List<StudentDtoOut> getLastStudents(Pageable pageable);

}
