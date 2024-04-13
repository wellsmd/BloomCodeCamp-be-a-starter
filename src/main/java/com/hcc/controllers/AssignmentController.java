package com.hcc.controllers;

import com.hcc.dto.AssignmentResponseDto;
import com.hcc.entities.Assignment;
import com.hcc.entities.User;
import com.hcc.exceptions.ResourceNotFoundException;
import com.hcc.services.AssignmentService;
import com.hcc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    @Autowired
    AssignmentService assignmentService;

    @Autowired
    UserService userService;

    @GetMapping("")
    public ResponseEntity<?> getAssignmentsByUser(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(assignmentService.findByUser(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAssignmentById(@PathVariable Long id, @AuthenticationPrincipal User user) throws ResourceNotFoundException {
        Optional<Assignment> assignmentOpt = assignmentService.findById(id);
        if (assignmentOpt.isPresent()) {
            return ResponseEntity.ok(assignmentOpt.get());
        } else {
            throw new ResourceNotFoundException("Assignment " + id + " not found.");
        }
        // return ResponseEntity.ok(new AssignmentResponseDto(assignmentOpt.orElse(new Assignment())));
    }

    @PostMapping("")
    public ResponseEntity<?> createAssignment(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(assignmentService.createAssignment(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAssignment(@PathVariable Long id,
                                              @RequestBody Assignment assignment,
                                              @AuthenticationPrincipal User user) {
//        if (assignment.getReviewer() != null) {
//            User reviewer = assignment.getReviewer();
//            reviewer = userService.findByUsername(reviewer.getUsername()).get();
//            assignment.setReviewer(reviewer);
//        }
        Assignment updatedAssignment = assignmentService.updateAssignment(assignment);
        return ResponseEntity.ok(updatedAssignment);
    }

}
