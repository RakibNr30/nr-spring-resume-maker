package com.resume.entity.ums;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Length(min = 3, max = 255)
    private String name;

    @Length(min = 3, max = 65535)
    @Column(length = 65535)
    private String about;

    @NotNull(message = "Date of birth field is required.")
    private LocalDate dob;

    @Length(min = 3, max = 20)
    @ColumnTransformer(read = "username", write = "LOWER(?)")
    @Pattern(regexp = "^[a-zA-Z_][a-zA-Z0-9_-]{3,20}$", message = "Username should be start with letter/underscore, no-space and length between 3 and 20.")
    private String username;

    @Email
    @Column(unique = true)
    private String email;

    @Pattern(regexp = "^[+]?[0-9]{8,}$", message = "Please enter a minimum 8 digit valid numeric mobile number. It should contain only digits and may start with a '+'.")
    private String mobile;

    @Length(max = 100)
    private String password;

    @Length(min = 3, max = 255)
    private String address;

    private Boolean enabled = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    @Fetch(value = FetchMode.SUBSELECT)
    @Size(min = 1)
    private List<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @OrderBy(value = "createdAt")
    private List<UserSkill> userSkills;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @OrderBy(value = "endDate DESC")
    private List<UserEducation> userEducations;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @OrderBy(value = "endDate DESC")
    private List<UserExperience> userExperiences;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @OrderBy(value = "createdAt DESC")
    private List<UserAward> userAwards;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @OrderBy(value = "createdAt")
    /*@JoinTable(name = "user_interests", joinColumns = {@JoinColumn(name = "id")}, inverseJoinColumns = {@JoinColumn(name = "user_id")})*/
    private List<UserInterest> userInterests;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @OrderBy(value = "createdAt")
    private List<UserLanguage> userLanguages;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @OrderBy(value = "createdAt")
    private List<UserSocialAccount> userSocialAccounts;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public boolean isEmpty() {
        return this.id == 0;
    }
}
