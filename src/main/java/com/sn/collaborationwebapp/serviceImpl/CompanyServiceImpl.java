package com.sn.collaborationwebapp.serviceImpl;

import com.sn.collaborationwebapp.entity.Company;
import com.sn.collaborationwebapp.entitydto.CompanyDto;
import com.sn.collaborationwebapp.exception.CompanyNameNotFoundException;
import com.sn.collaborationwebapp.exception.ResourceNotFoundException;
import com.sn.collaborationwebapp.repositories.CompanyRepo;
import com.sn.collaborationwebapp.service.CompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
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
    Company company=this.companyRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Company","id",id));
        return this.modelMapper.map(company,CompanyDto.class);
    }
    @Override
    public CompanyDto updateCompanyById(CompanyDto companyDto, Long id) {
        Company company=this.companyRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Company","id",id));
        company.setCompanyImage(companyDto.getCompanyImage());
        company.setCompanyName(companyDto.getCompanyName());
        company.setCompanyDomain(companyDto.getCompanyDomain());
        company.setCompanyEmail(companyDto.getCompanyEmail());
        company.setCompanyAddress(companyDto.getCompanyAddress());
        company.setCompanyPassword(companyDto.getCompanyPassword());
        company.setMobileNumber(companyDto.getMobileNumber());
        company.setUpdatedDate(new Date());
        Company updatedCompany = this.companyRepo.save(company);
        return this.modelMapper.map(updatedCompany,CompanyDto.class);

    }

    @Override
    public void deleteCompany(Long id) {
    Company company=this.companyRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Company","id",id));
    this.companyRepo.delete(company);
    }
    @Override
    public Optional<CompanyDto> getCompanyByName(String companyName) {
        Optional<Company> companyOptional = companyRepo.findByCompanyName(companyName);
        return companyOptional.map(company -> modelMapper.map(company, CompanyDto.class));
    }
    @Override
    public void deleteCompanyByName(String name) {
    Company company=this.companyRepo.findByCompanyName(name).orElseThrow(()-> new CompanyNameNotFoundException("Company","name",name));
    this.companyRepo.delete(company);
    }
    @Override
    public List<Company> serchCompanyByFirstLetter(String letter) {
        return companyRepo.findByCompanyNameStartingWith(letter);
    }

}
