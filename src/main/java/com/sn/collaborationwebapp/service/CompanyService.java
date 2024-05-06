package com.sn.collaborationwebapp.service;

import com.sn.collaborationwebapp.entity.Company;
import com.sn.collaborationwebapp.entitydto.CompanyDto;

import java.util.List;
import java.util.Optional;

public interface CompanyService {

    CompanyDto createCompany(CompanyDto companyDto);
    List<Company> getAllCompanies();
    CompanyDto getCompanyById(Long id);
    CompanyDto updateCompany(CompanyDto companyDto, Long id);
    Optional<CompanyDto> getCompanyByName(String name);
    void deleteCompany(Long id);
    void deleteCompanyByName(String name);
    void validateCompany(CompanyDto companyDto);
    List<CompanyDto> serchCompanyByFirstLetter(String name);
}
