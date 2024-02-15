package com.eric.rizz.school;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SchoolController {
    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }


    @PostMapping("/schools")
    public SchoolDTO create(
            @RequestBody SchoolDTO dto
    ) {

        return schoolService.create(dto);
    }

    private School toSchool(SchoolDTO dto) {
        return new School(dto.name());
    }

    private SchoolDTO toSchoolDTO(School school) {
        return new SchoolDTO(school.getName());
    }

    @GetMapping("/schools")
    public List<SchoolDTO> findAll()
    {
        return schoolService.findAll();

    }

}
