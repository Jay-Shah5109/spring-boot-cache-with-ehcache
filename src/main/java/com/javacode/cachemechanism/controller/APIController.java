package com.javacode.cachemechanism.controller;

import com.javacode.cachemechanism.model.Student;
import com.javacode.cachemechanism.service.APIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class APIController {

    @Autowired
    private APIService apiService;

    @GetMapping
    public ResponseEntity<Student> fetchStudent(@RequestParam(name = "studentID") String studentID,
                                                @RequestParam(name = "isCacheable") boolean isCacheable)
            throws InterruptedException {
        return new ResponseEntity<>(apiService.fetchStudent(studentID, isCacheable).get(), HttpStatus.OK);
    }

}
