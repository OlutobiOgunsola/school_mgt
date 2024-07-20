package com.tobi.example.Student;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tobi.example.StudentProfile.StudentProfile;
import com.tobi.example.school.School;
import jakarta.persistence.*;

@Entity
@Table(name="T_STUDENT")
public class Student {
    @Id
    @GeneratedValue
    private Integer id;

    private String firstName;
    private String lastName;

    @OneToOne(
            mappedBy="student",
            cascade=CascadeType.ALL
    )
    private StudentProfile studentProfile;

    @Column(
            unique = true
    )
    private String email;

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name="school_id"
    )
    @JsonBackReference
    private School school;

    private Integer age;

    public Student() {
    }

    public Student(String firstName, String lastName, String email, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public StudentProfile getStudentProfile() {
        return studentProfile;
    }

    public void setStudentProfile(StudentProfile studentProfile) {
        this.studentProfile = studentProfile;
    }
}
