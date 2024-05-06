package com.sn.collaborationwebapp.entitydto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Data
@NoArgsConstructor
public class CompanyDto {

    private Long id;

    @NotEmpty(message = "Company image is required.")
    private String companyImage;

    @NotEmpty(message = "Company name is required.")
    @Column(nullable = false, length = 100)
    private String companyName;

    @NotEmpty(message = "Company domain is required.")
    private String companyDomain;

    @Column(unique = true)
    @Email(message = "Invalid email address.")
    @NotEmpty(message = "Company email is required.")
    private String companyEmail;

    @NotEmpty(message = "Password is required.")
    private String companyPassword;

    @NotEmpty
    private String companyAddress;

    @Size(min = 10, max = 15, message = "Mobile number must be between 10 and 15 characters.")
    @Pattern(regexp = "\\d{10,15}", message = "Mobile number must contain only digits.")
    private String mobileNumber;

    public void hashPassword() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(this.companyPassword);

        // Log the hashed password length for debugging
        System.out.println("Hashed Password Length: " + hashedPassword.length());

        // Ensure hashed password length is within the limits
        if (hashedPassword.length() > 60) {
            // Handle the situation where the hashed password is too long
            throw new RuntimeException("Hashed password length exceeds column length.");
        }

        this.companyPassword = hashedPassword;
    }
}
