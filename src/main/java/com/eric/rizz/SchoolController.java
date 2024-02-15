package com.eric.rizz;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SchoolController {
    private final SchoolRepository schoolRepository;

    public SchoolController(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @PostMapping("/schools")
    public SchoolDTO create(
            @RequestBody SchoolDTO dto
    ) {
        var school = toSchool(dto);
        var savedSchool = schoolRepository.save(school);
        return dto;
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
        return schoolRepository.findAll()
                .stream()
                .map(this::toSchoolDTO)
                .collect(Collectors.toList());

    }

}
