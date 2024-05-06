package com.sn.collaborationwebapp.serviceImpl;

import com.sn.collaborationwebapp.entity.Company;
import com.sn.collaborationwebapp.entitydto.CompanyDto;
import com.sn.collaborationwebapp.repositories.CompanyRepo;
import com.sn.collaborationwebapp.service.CompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CompanyRepo companyRepo;

    @Override
    public CompanyDto createCompany(CompanyDto companyDto) {
        Company company = modelMapper.map(companyDto, Company.class);
        company.setRegisterDate(new Date());
        // Ensure that the company password is hashed before saving
        company.hashPassword();

        Company savedCompany = companyRepo.save(company);
        return modelMapper.map(savedCompany, CompanyDto.class);
    }

    @Override
    public List<Company> getAllCompanies() {
        List<Company> companies = companyRepo.findAll();
/*
//        return companies.stream()
//                .map(company -> modelMapper.map(company, CompanyDto.class))
//                .collect(Collectors.toList());
*/
        return companies;
    }

    @Override
    public CompanyDto getCompanyById(Long id) {
        return null;
    }

    @Override
    public CompanyDto updateCompany(CompanyDto companyDto, Long id) {
        return null;
    }

    @Override
    public Optional<CompanyDto> getCompanyByName(String name) {
        return Optional.empty();
    }

    @Override
    public void deleteCompany(Long id) {

    }

    @Override
    public void deleteCompanyByName(String name) {

    }

    @Override
    public void validateCompany(CompanyDto companyDto) {

    }

    @Override
    public List<CompanyDto> serchCompanyByFirstLetter(String name) {
        return null;
    }
}
