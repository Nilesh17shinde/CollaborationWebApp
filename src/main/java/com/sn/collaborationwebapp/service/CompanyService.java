package com.sn.collaborationwebapp.service;

import com.sn.collaborationwebapp.entity.Company;
import com.sn.collaborationwebapp.entitydto.CompanyDto;

import java.util.List;
import java.util.Optional;

public interface CompanyService {

    CompanyDto createCompany(CompanyDto companyDto);
    List<Company> getAllCompanies();
    CompanyDto getCompanyById(Long id);
    CompanyDto updateCompanyById(CompanyDto companyDto, Long id);
    Optional<CompanyDto> getCompanyByName(String companyName);
    void deleteCompany(Long id);
    void deleteCompanyByName(String name);
    List<Company> serchCompanyByFirstLetter(String letter);
}
