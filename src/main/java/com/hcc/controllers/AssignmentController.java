package com.hcc.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {


    @PostMapping("")
    public ResponseEntity<?> createAssignment() {
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAssignment() {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAssignmentById() {
        return null;
    }

    @GetMapping("")
    public ResponseEntity<?> getAllAssignments() {
        return null;
    }

}
