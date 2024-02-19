package com.eric.rizz.student;

import com.eric.rizz.school.School;
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
    @ResponseStatus(HttpStatus.ACCEPTED)
    public StudentResponseDTO saveStudent(
            @Valid @RequestBody StudentDTO dto
    ) {
        return this.studentService.saveStudent(dto);
    }

    private Student toStudent(StudentDTO dto) {
        var student = new Student();
        student.setFirstname(dto.firstname());
        student.setLastname(dto.lastname());
        student.setEmail(dto.email());
        var school = new School();
        school.setId((dto.schoolId()));

        student.setSchool(school);

        return student;
    }

    private StudentResponseDTO toStudentResponseDTO(Student student) {
        return new StudentResponseDTO(
                student.getFirstname(),
                student.getLastname(),
                student.getEmail()
        );
    }

    @GetMapping("/students")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<StudentResponseDTO> findAllStudent() {
        return studentService.findAllStudent();
    }

    @GetMapping("/students/{student-id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public StudentResponseDTO findStudentById(
                @PathVariable("student-id") Integer id
            ) {

        return studentService.findStudentById(id);
    }


    @GetMapping("/students/search/{student-name}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<StudentResponseDTO> findStudentByName(
            @PathVariable("student-name") String name
    ) {

        return this.studentService.findStudentByName(name);

    }

    @DeleteMapping("/students/{student-id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(
            @PathVariable("student-id") Integer id
    ) {

        this.studentService.delete(id);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException (
        MethodArgumentNotValidException exp
    ) {
        var errors = new HashMap<String, String>();
        exp.getBindingResult().getAllErrors()
                .forEach(error -> {
                    var fieldName = ((FieldError) error).getField();
                    var errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }



}
