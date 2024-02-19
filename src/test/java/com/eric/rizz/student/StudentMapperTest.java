package com.eric.rizz.student;

import org.junit.jupiter.api.AfterEach;
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
    public void shouldMapStudentDTOToStudent() {
        StudentDTO dto = new StudentDTO("John",
                "Doe",
                "john@mail.com",
                1
                );

        Student student = mapper.toStudent(dto);

        assertEquals(dto.firstname(), student.getFirstname());
        assertEquals(dto.lastname(), student.getLastname());
        assertEquals(dto.email(), student.getEmail());
        assertNotNull(student.getSchool());
        assertEquals(dto.schoolId(), student.getSchool().getId());

    }

    @Test
    public void should_throw_studentDto_to_student_when_studentDto_is_null() {
        var exp = assertThrows(NullPointerException.class, () -> mapper.toStudent(null));
        assertEquals("The student DTO should not be null", exp.getMessage());
    }



    @Test
    public void shouldMapStudentDTOToStudentResponseDTO() {
        Student student = new Student("Jane",
                "Smith",
                "jane@mail.com",
                20
        );

        StudentResponseDTO response = mapper.toStudentResponseDTO(student);

        assertEquals(response.firstname(), student.getFirstname());
        assertEquals(response.lastname(), student.getLastname());
        assertEquals(response.email(), student.getEmail());

    }




}