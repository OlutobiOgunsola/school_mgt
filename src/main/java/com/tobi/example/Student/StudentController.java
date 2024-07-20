package com.tobi.example.Student;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/students")
    @ResponseStatus(HttpStatus.CREATED)
    public StudentResponseDto saveStudent(@Valid @RequestBody StudentDto studentDto) {
        return studentService.saveStudent(studentDto);
    }

    @GetMapping("/students/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentResponseDto getStudentById(@PathVariable("id") Integer id) {
        return studentService.getStudentById(id);
    }

    @GetMapping("/students")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentResponseDto> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/students/search/{studentName}")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentResponseDto> getStudentsByName(@PathVariable("studentName") String studentName) {
        return studentService.getStudentsByName(studentName);
    }

    @DeleteMapping("/students/{student-id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudent (@PathVariable("student-id") Integer studentId) {
        studentService.deleteStudent(studentId);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exp) {
        var errors = new HashMap<String,String>();
        exp.getBindingResult().getAllErrors().forEach(error -> {
            var fieldName = ((FieldError) error).getField();
            var errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

                return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
