package com.example.WMS_JB_Springs_V10.controller;

import com.example.WMS_JB_Springs_V10.model.Admin;
import com.example.WMS_JB_Springs_V10.model.Employee;
import com.example.WMS_JB_Springs_V10.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {



    @GetMapping(value = "/warehouse/getAllEmployees")
    public List<Employee> getAllEmployees(){
        return UserRepository.getAllEmployees();
    }

    @GetMapping (value = "/warehouse/getAllAdmins")
    public List<Admin> getAllAdmins(){
        return UserRepository.getAllAdmins();
    }
}
