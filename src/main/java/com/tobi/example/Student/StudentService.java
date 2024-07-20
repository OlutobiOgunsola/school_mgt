package com.tobi.example.Student;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository repository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository repository, StudentMapper studentMapper) {
        this.repository = repository;
        this.studentMapper = studentMapper;
    }

    public StudentResponseDto saveStudent (StudentDto studentDto) {
        var studentObject = studentMapper.toStudent(studentDto);
        var savedStudent = repository.save(studentObject);

        return studentMapper.toStudentResponseDto(savedStudent);

    }

    public StudentResponseDto getStudentById(Integer Id) {
        return repository.findById(Id).map(studentMapper::toStudentResponseDto).orElse(null);
    }

    public List<StudentResponseDto> getAllStudents() {
        return repository.findAll().stream().map(studentMapper::toStudentResponseDto).collect(Collectors.toList());
    }

    public List<StudentResponseDto> getStudentsByName(String name) {
        return repository.findAllByfirstNameContaining(name).stream().map(studentMapper::toStudentResponseDto).collect(Collectors.toList());
    }

    public void deleteStudent(Integer Id) {
        repository.deleteById(Id);
    }
}
