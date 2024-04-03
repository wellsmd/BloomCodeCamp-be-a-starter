package com.hcc.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AssignmentEnum {
    ASSIGNMENT_01(1, "Docker"),
    ASSIGNMENT_02(2, "Spring Boot"),
    ASSIGNMENT_03(3, "PostgreSQL"),
    ASSIGNMENT_04(4, "FORTRAN V"),
    ASSIGNMENT_05(5, "COBOL for the 21st Century");

    private int assignmentNumber;

    private String assignmentName;
    AssignmentEnum(int assignmentNumber, String assignmentName) {
        this.assignmentNumber = assignmentNumber;
        this.assignmentName = assignmentName;
    }

    int getAssignmentNumber() {
        return assignmentNumber;
    }

    String getAssignmentName() {
        return assignmentName;
    }
}
