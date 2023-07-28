package ru.hogwarts.school.controlller;
import org.junit.jupiter.api.AfterEach;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import ru.hogwarts.school.dto.FacultyDtoIn;
import ru.hogwarts.school.dto.FacultyDtoOut;
import ru.hogwarts.school.dto.StudentDtoIn;
import ru.hogwarts.school.dto.StudentDtoOut;
import ru.hogwarts.school.entity.Faculty;
import ru.hogwarts.school.entity.Student;
import ru.hogwarts.school.mapper.FacultyMapper;
import ru.hogwarts.school.mapper.StudentMapper;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.StudentService;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//@WebMvcTest(controllers = StudentController.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private StudentRepository studentRepository;

    private final Faker faker = new Faker();

    @Test
    public StudentDtoOut createTest() {
        StudentDtoIn studentDtoIn = generateDto();
        ResponseEntity<StudentDtoOut> responseEntity = testRestTemplate.postForEntity(
                "http://localhost:"+port+"/students",
                studentDtoIn,
                StudentDtoOut.class
        );
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        StudentDtoOut studentDtoOut = responseEntity.getBody();

        assertThat(studentDtoOut).isNotNull();
        assertThat(studentDtoOut.getId()).isNotEqualTo(1L);
        assertThat(studentDtoOut.getAge()).isEqualTo(studentDtoIn.getAge());
        assertThat(studentDtoOut.getName()).isEqualTo(studentDtoIn.getName());

        return studentDtoOut;
    }

    @Test
    public void updateStudent() {
        StudentDtoOut created = createTest();
        StudentDtoIn studentDtoIn = new StudentDtoIn();
        studentDtoIn.setName(faker.name().fullName());
        studentDtoIn.setAge(created.getAge());

        ResponseEntity<StudentDtoOut> responseEntity = testRestTemplate.exchange(
                "http://localhost:" + port + "/students/" + created.getId(),
                HttpMethod.PUT,
                new HttpEntity<>(studentDtoIn),
                StudentDtoOut.class
        );
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        StudentDtoOut studentDtoOut = responseEntity.getBody();

        assertThat(studentDtoOut).isNotNull();
        assertThat(studentDtoOut.getId()).isEqualTo(created.getId());
        assertThat(studentDtoOut.getAge()).isEqualTo(studentDtoIn.getAge());
        assertThat(studentDtoOut.getName()).isEqualTo(studentDtoIn.getName());

        // checking not found
        long incorrectId = created.getId() + 1;

        ResponseEntity<String> stringResponseEntity = testRestTemplate.exchange(
                "http://localhost:" + port + "/students/" + incorrectId,
                HttpMethod.PUT,
                new HttpEntity<>(studentDtoIn),
                String.class
        );
        assertThat(stringResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(stringResponseEntity.getBody())
                .isEqualTo("Студент с id = " + incorrectId + " не найден!");
    }


    private StudentDtoIn generateDto() {
        StudentDtoIn studentDtoIn = new StudentDtoIn();
        studentDtoIn.setAge(faker.random().nextInt(10, 50));
        studentDtoIn.setName(faker.name().fullName());
        return studentDtoIn;
    }
}

