package com.eric.rizz.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;
    @Mock
    private StudentRepository repository;
    @Mock
    private StudentMapper studentMapper;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    public void should_successfully_save_a_student() {
        // Given
        StudentDTO dto = new StudentDTO("John",
                "Doe",
                "john@mail.com",
                1
        );

        Student student = new Student("Jane",
                "Smith",
                "jane@mail.com",
                20
        );

        Student savedStudent = new Student("John",
                "Doe",
                "john@mail.com",
                20
        );

        savedStudent.setId(1);


        // Mock the calls
        when(studentMapper.toStudent(dto))
                .thenReturn(student);

        when(repository.save((student))).
                thenReturn(savedStudent);
        when(studentMapper.toStudentResponseDTO(savedStudent))
                .thenReturn(new StudentResponseDTO(
                        "John",
                        "Doe",
                        "john@mail.com"
                ));


        // When
        StudentResponseDTO responseDTO = studentService.saveStudent(dto);

        // Then
        assertEquals(dto.firstname(), responseDTO.firstname());
        assertEquals(dto.lastname(), responseDTO.lastname());
        assertEquals(dto.email(), responseDTO.email());

        verify(studentMapper, times(1)).toStudent(dto);
        verify(repository, times(1)).save(student);
        verify(studentMapper, times(1)).toStudent(dto);

    }


    @Test
    public void should_return_all_students() {
        // Given
        List<Student> students = new ArrayList<>();
        students.add(new Student("John",
                "Doe",
                "john@mail.com",
                1
        ));

        // Mock the calls
        when(repository.findAll()).thenReturn(students);
        when(studentMapper.toStudentResponseDTO(any(Student.class)))
                .thenReturn(new StudentResponseDTO(
                        "John",
                        "Doe",
                        "john@mail.com"
                ));

        // When
        List<StudentResponseDTO> responseDTOS = studentService.findAllStudent();

        // Then
        assertEquals(students.size(), responseDTOS.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void should_return_student_by_id() {
        // Given
        Integer studentId = 1;
        Student student = new Student("John",
                "Doe",
                "john@mail.com",
                20
        );

        // Mock the calls
        when(repository.findById(studentId))
                .thenReturn(Optional.of(student));
        when(studentMapper.toStudentResponseDTO(any(Student.class)))
                .thenReturn(new StudentResponseDTO(
                        "John",
                        "Doe",
                        "john@mail.com"
                ));

        // When
        StudentResponseDTO dto = studentService.findStudentById(studentId);


        // Then

        assertEquals(dto.firstname(), student.getFirstname());
        assertEquals(dto.lastname(), student.getLastname());
        assertEquals(dto.email(), student.getEmail());

        verify(repository, times(1)).findById(studentId);
    }


    @Test
    public void should_find_student_by_name() {
        // Given
        String studentName = "John";
        List<Student> students = new ArrayList<>();
        students.add(new Student("John",
                "Doe",
                "john@mail.com",
                1
        ));

        // Mock the calls
        when(repository.findAllByFirstnameContaining(studentName))
                .thenReturn(students);
        when(studentMapper.toStudentResponseDTO(any(Student.class)))
                .thenReturn(new StudentResponseDTO(
                        "John",
                        "Doe",
                        "john@mail.com"
                ));


        // When
        var responseDTO = studentService.findStudentByName(studentName);

        // Then
        assertEquals(students.size(), responseDTO.size());
        verify(repository, times(1))
                .findAllByFirstnameContaining(studentName);

    }












}