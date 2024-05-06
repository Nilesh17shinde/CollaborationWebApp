package com.sn.collaborationwebapp.controller;

import com.sn.collaborationwebapp.entity.Company;
import com.sn.collaborationwebapp.entitydto.CompanyDto;
import com.sn.collaborationwebapp.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @PostMapping("/add")
    public ResponseEntity<CompanyDto>createCompany(@Valid @RequestBody CompanyDto companyDto ){
        CompanyDto saveCompany=this.companyService.createCompany(companyDto);
        return new ResponseEntity<>(saveCompany, HttpStatus.CREATED);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Company>> getAllCompanies() {
        return ResponseEntity.ok(this.companyService.getAllCompanies());
    }
}