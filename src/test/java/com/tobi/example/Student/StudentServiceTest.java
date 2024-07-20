package com.tobi.example.Student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldSuccessfullySaveStudent() {
        StudentDto dto = new StudentDto(
                "John",
                "Doe",
                "john@email.com",
                15
        );

        Student student = new Student(
                "John",
                "Doe",
                "john@email.com",
                15
        );

        Student savedStudent = new Student(
                "John",
                "Doe",
                "john@email.com",
                15
        );

        savedStudent.setId(1);

        Mockito.when(studentMapper.toStudent(dto)).thenReturn(student);
        Mockito.when(studentRepository.save(student)).thenReturn(savedStudent);
        Mockito.when(studentMapper.toStudentResponseDto(savedStudent)).thenReturn(new StudentResponseDto("John", "Doe", "john@email.com"));

        StudentResponseDto responseDto = studentService.saveStudent(dto);

        assertEquals(dto.firstName(), responseDto.firstName());
        assertEquals(dto.lastName(), responseDto.lastName());
        assertEquals(dto.email(), responseDto.email());

        Mockito.verify(studentMapper, Mockito.times(1)).toStudent(dto);
        Mockito.verify(studentRepository, Mockito.times(1)).save(student);
        Mockito.verify(studentMapper, Mockito.times(1)).toStudentResponseDto(savedStudent);
    }

    @Test
    public void shouldGetAllStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(
                "John",
                "Doe",
                "john@email.com",
                15
        ));

        Mockito.when(studentRepository.findAll()).thenReturn(students);
        Mockito.when(studentMapper.toStudentResponseDto(Mockito.any(Student.class))).thenReturn(new StudentResponseDto("John", "Doe", "john@email.com"));

        List<StudentResponseDto> responseDtos = studentService.getAllStudents();

        assertEquals(students.size(), responseDtos.size());
    }

    @Test
    public void shouldGetStudentById() {
        Student student = new Student(
                "John",
                "Doe",
                "john@email.com",
                15
        );

        Mockito.when(studentRepository.findById(1)).thenReturn(java.util.Optional.of(student));
        Mockito.when(studentMapper.toStudentResponseDto(student)).thenReturn(new StudentResponseDto("John", "Doe", "john@email.com"));

        StudentResponseDto responseDto = studentService.getStudentById(1);

        assertEquals("John", responseDto.firstName());
        assertEquals("Doe", responseDto.lastName());
        assertEquals("john@email.com", responseDto.email());

        Mockito.verify(studentRepository, Mockito.times(1)).findById(1);
        Mockito.verify(studentMapper, Mockito.times(1)).toStudentResponseDto(student);
    }

    @Test
    public void shouldGetStudentByName() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(
                "John",
                "Doe",
                "john@email.com",
                15
        ));

        Mockito.when(studentRepository.findAllByfirstNameContaining("John")).thenReturn(students);
        Mockito.when(studentMapper.toStudentResponseDto(Mockito.any(Student.class))).thenReturn(new StudentResponseDto("John", "Doe", "john@email.com"));

        List<StudentResponseDto> responseDtos = studentService.getStudentsByName("John");

        assertEquals(students.size(), responseDtos.size());
    }
}