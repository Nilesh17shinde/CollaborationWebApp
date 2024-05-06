package com.sn.collaborationwebapp.serviceImpl;

import com.sn.collaborationwebapp.entity.Admin;
import com.sn.collaborationwebapp.exception.AdminAlreadyExistsException;
import com.sn.collaborationwebapp.repositories.AdminRepo;
import com.sn.collaborationwebapp.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.List;
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepo adminRepo;

    @Override
    @Transactional
    public Admin createAdmin(Admin admin) {
        if (adminRepo.existsByEmail(admin.getEmail())) {
            throw new AdminAlreadyExistsException("Admin with Email " + admin.getEmail() + " already exists.");
        } else {
            admin.setRegisterDate(new Date());
            validateAdmin(admin);
            admin.hashPassword(); // Hash the password before saving
            return this.adminRepo.save(admin);
        }
    }

    @Override
    public List<Admin> getAllAdmin() {
        return adminRepo.findAll();
    }

    private void validateAdmin(Admin admin) {
        if (admin.getEmail() == null || admin.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty.");
        }
    }
}