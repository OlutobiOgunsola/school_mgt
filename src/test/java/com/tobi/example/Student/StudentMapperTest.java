package com.tobi.example.Student;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {

    private StudentMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new StudentMapper();
    }

    @Test
    public void shouldMapStudentDtoToStudent() {
        StudentDto dto = new StudentDto("John", "Doe", "john@mail.com", 1);
        Student student = mapper.toStudent(dto);

        assertEquals(dto.firstName(), student.getFirstName());
        assertEquals(dto.lastName(), student.getLastName());
        assertEquals(dto.email(), student.getEmail());
        assertNotNull(student.getSchool());
        assertEquals(dto.schoolId(), student.getSchool().getId());
    }

    @Test
    public void shouldMapStudentToStudentDto() {
        Student student = new Student("Jane", "Smith", "jane@email.com", 10);
        StudentResponseDto responseDto = mapper.toStudentResponseDto(student);

        assertEquals(responseDto.firstName(), student.getFirstName());
        assertEquals(responseDto.lastName(), student.getLastName());
        assertEquals(responseDto.email(), student.getEmail());

    }

    @Test
    public void shouldThrowNullPointerExceptionWhenStudentWhenStudentDtoIsNull () {
        StudentDto dto = null;
        Student student = mapper.toStudent(dto);

        assertThrows(NullPointerException.class, () -> mapper.toStudent(dto));
    }
}