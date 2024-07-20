package com.tobi.example.school;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SchoolController {

    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping("/schools")
    @ResponseStatus(HttpStatus.CREATED)
    public SchoolDto create(@RequestBody SchoolDto schoolObject) {
        schoolService.createSchool(schoolObject);
        return schoolObject;
    }

    @GetMapping("/schools")
    public List<SchoolDto> getAll() {
        return schoolService.getAll();
    }
}
