package com.av.departmentservice.resource;

import com.av.departmentservice.dto.DepartmentDto;
import com.av.departmentservice.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/department")
public class DepartmentResource {

    @Autowired
    public DepartmentService departmentService;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity list() {
        return departmentService.list();
    }

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity add(@RequestBody DepartmentDto dto) {
        return departmentService.add(dto);
    }
}
