package com.sn.collaborationwebapp.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String companyImage;

    @NotEmpty
    @Column(unique = true, nullable = false, length = 100)
    private String companyName;

    @NotEmpty
    private String companyDomain;

    @Column(unique = true, nullable = false)
    @Email(message = "Invalid email address.")
    @NotEmpty
    private String companyEmail;

    @NotEmpty
    private String companyAddress;

    @NotEmpty
    private String companyPassword;

    @Size(min = 10, max = 15, message = "Mobile number must be between 10 and 15 characters.")
    @Pattern(regexp = "\\d{10,15}", message = "Mobile number must contain only digits.")
    private String mobileNumber;

    @Temporal(TemporalType.TIMESTAMP)
    private Date registerDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Post> posts = new ArrayList<>();

    public void hashPassword() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!this.companyPassword.startsWith("$2a$")) { // Check if password is already encoded
            this.companyPassword = passwordEncoder.encode(this.companyPassword);
        }
    }
}
