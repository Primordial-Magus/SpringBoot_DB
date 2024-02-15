package com.eric.rizz;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class StudentController {

    private final StudentRepository repository;

    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

//    @GetMapping("/hello")
//    public String sayHello() {
//        return "Hello from first controller";
//    }
//
//    @PostMapping("/post")
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    public String post(
//            @RequestBody String message
//    ) {
//        return "Request accepted and message is : " + message;
//    }

    @PostMapping("/students")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Student post(
            @RequestBody StudentDTO dto
    ) {
        var student = toStudent(dto);
        return repository.save(student);
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

    @GetMapping("/students")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Student> findAllStudent() {
        return repository.findAll();
    }

    @GetMapping("/students/{student-id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Student findStudentById(
                @PathVariable("student-id") Integer id
            ) {

        return repository.findById(id).
                orElse(new Student());

    }


    @GetMapping("/students/search/{student-name}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Student> findStudentByName(
            @PathVariable("student-name") String name
    ) {

        return repository.findAllByFirstnameContaining(name);

    }

    @DeleteMapping("/students/{student-id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(
            @PathVariable("student-id") Integer id
    ) {

        repository.deleteById(id);
    }



}
