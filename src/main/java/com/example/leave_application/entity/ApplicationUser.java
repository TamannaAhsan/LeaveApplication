package com.example.leave_application.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="applicationUser")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationUser {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )

    private Integer userId;

    @Column(name="user_email", nullable = false, length = 150)
    private String email;

    @Column(nullable = false)
    private String password;

    //private String accessToken;
    //private Date tokenExpireDate;

    @Column(nullable = false, length = 100)
    private String username;

    //Self Referencing//
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "managerId"
    )
    private ApplicationUser manager;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "applicationUserRoles",
            joinColumns = @JoinColumn(name = "user", referencedColumnName = "userId"),
            inverseJoinColumns = @JoinColumn(name = "role", referencedColumnName = "roleId")
    )
    private Set<ApplicationRole> roles = new HashSet<>();

}
