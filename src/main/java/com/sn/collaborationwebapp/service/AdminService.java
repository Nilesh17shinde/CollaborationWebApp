// AdminService.java
package com.sn.collaborationwebapp.service;

import com.sn.collaborationwebapp.entity.Admin;

import java.util.List;

public interface AdminService {
    Admin createAdmin(Admin admin);
    List<Admin> getAllAdmin();
}
