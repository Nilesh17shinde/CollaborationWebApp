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
import java.util.Optional;

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
    @GetMapping("/getById/{id}")
    public ResponseEntity<CompanyDto> getCompanyById(@PathVariable Long id) {
        CompanyDto companyDto=this.companyService.getCompanyById(id);
        return new ResponseEntity<CompanyDto>(companyDto,HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CompanyDto> updateCompanyById(@Valid @RequestBody CompanyDto companyDto,
                                                        @PathVariable Long id) {
        CompanyDto companyDto1=this.companyService.updateCompanyById(companyDto, id);
        return new ResponseEntity<CompanyDto>(companyDto1,HttpStatus.OK);
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<CompanyDto> getCompanyByName(@PathVariable String name) {
        Optional<CompanyDto> companyOptional = companyService.getCompanyByName(name);
        return companyOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
        this.companyService.deleteCompany(id);
        final String string = "Company with Id " + id + " deleted successfully";
        return ResponseEntity.ok(string);
    }

    @DeleteMapping("/deleteByName/{name}")
    public ResponseEntity<String> deleteCompanyByName(@PathVariable String name) {
        this.companyService.deleteCompanyByName(name);
        final String string = "Company with Name " + name + " deleted successfully";
        return ResponseEntity.ok(string);
    }

    @GetMapping("/search/{letter}")
    public ResponseEntity<List<Company>> searchCompanyByFirstLetter(@PathVariable String letter) {
        List<Company> companies = companyService.serchCompanyByFirstLetter(letter);
        if (companies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }
}