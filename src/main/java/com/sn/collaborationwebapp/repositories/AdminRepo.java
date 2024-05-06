// AdminRepo.java
package com.sn.collaborationwebapp.repositories;

import com.sn.collaborationwebapp.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepo extends JpaRepository<Admin, Long> {
    boolean existsByEmail(String email);

}
