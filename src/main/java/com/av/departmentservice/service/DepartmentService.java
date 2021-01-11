package com.av.departmentservice.service;

import com.av.departmentservice.dto.DepartmentDto;
import com.av.departmentservice.exception.DepartmentException;
import com.av.departmentservice.model.Department;
import com.av.departmentservice.repository.DepartmentRepository;
import com.av.departmentservice.util.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DepartmentService {

    @Autowired
    public DepartmentRepository repository;

    public ResponseEntity list() {
        try {
            List<Department> departmentList = repository.findAll();
            List<DepartmentDto> departmentDtos = departmentList.stream()
                    .map(x -> new DepartmentDto(x.getId(), x.getCode(), x.getName()))
                    .collect(Collectors.toList());

            if (CollectionUtils.isEmpty(departmentDtos)) {
                log.error("No departments");
                throw new DepartmentException("No departments available");
            } else {
                return new ResponseEntity<>(departmentDtos, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity add(DepartmentDto dto) {
        try {
            Department department = new Department();
            department.setCode(dto.getCode());
            department.setName(dto.getName());
            return new ResponseEntity<>(repository.save(department), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }


}
