package com.is.teamTasker.controller;

import com.is.teamTasker.entity.Role;
import com.is.teamTasker.entity.Status;
import com.is.teamTasker.model.AppResponse;
import com.is.teamTasker.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/admin/role/")
public class RoleController {

    private RoleRepository roleRepository;

    @Autowired
    public RoleController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostMapping("create")
    public ResponseEntity create(@Valid @RequestBody Role role) {

        roleRepository.save(new Role(role.getName().toUpperCase(), Status.ACTIVE));

        return ResponseEntity.ok(new AppResponse(1, "Created"));
    }

    @GetMapping("getAll")
    public ResponseEntity getAll() {

        return ResponseEntity.ok(new AppResponse(1, "OK", roleRepository.findAll()));
    }
}
