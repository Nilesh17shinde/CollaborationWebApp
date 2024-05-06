package com.sn.collaborationwebapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
    @Column(nullable = false, length = 100)
    private String companyName;

    @NotEmpty
    private String companyDomain;

    @Column(unique = true)
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    @JsonBackReference
    private Admin admin;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Post> posts = new HashSet<>();

    public void hashPassword() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.companyPassword = passwordEncoder.encode(this.companyPassword);
    }
}
