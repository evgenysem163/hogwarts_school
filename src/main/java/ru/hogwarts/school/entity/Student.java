package ru.hogwarts.school.entity;

import javax.persistence.*;

@Entity
    @Table(name = "students")
    public class Student {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        private int age;

    public Student(Long id, String name, int age, Faculty faculty) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.faculty = faculty;
    }

    public Student() {
    }

    @ManyToOne
        @JoinColumn(name = "faculty_id")
        private Faculty faculty;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Faculty getFaculty() {
            return faculty;
        }

        public void setFaculty(Faculty faculty) {
            this.faculty = faculty;
        }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", faculty=" + faculty +
                '}';
    }
}



