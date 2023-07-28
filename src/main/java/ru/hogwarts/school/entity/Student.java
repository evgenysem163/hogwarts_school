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
        private int avatar;

    public Student() {
    }

    public Student(Long id, String name, int age, int avatar) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.avatar = avatar;
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

    public Object getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }
}



