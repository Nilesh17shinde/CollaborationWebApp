package com.sn.collaborationwebapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, max = 255)
    private String postDescription;

    @NotNull
    @Size(min = 1, max = 255)
    private String postTitle;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date postReleaseDate;

    @Temporal(TemporalType.DATE)
    @Future
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date futureDate;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedDate;

    @NotNull
    @Pattern(regexp = "active|inactive")
    private String postStatus;

    @NotNull
    private String postType;

    @NotEmpty
    @Email
    private String email;

    @Size(min = 10, max = 15, message = "Mobile number must be between 10 and 15 characters.")
    @NotEmpty
    private String mobileNumber;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "company_id")
    private Company company;


}
