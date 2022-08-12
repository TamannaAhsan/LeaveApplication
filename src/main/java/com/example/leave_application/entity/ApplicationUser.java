package com.example.leave_application.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name="User")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationUser implements UserDetails {

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

    private Integer id;

    @Column(name="user_email", nullable = false, length = 150)
    private String email;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    private String accessToken;
    private Date tokenExpireDate;

    @Column(nullable = false, length = 100)
    private String username;

    //Self Referencing//
    @ManyToOne
    @JoinColumn(name = "managerId"
    )
    private ApplicationUser manager;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "User_Role",
            joinColumns = @JoinColumn(name = "user", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role", referencedColumnName = "roleId")
    )
    private Set<ApplicationRole> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities= this.roles.stream().map((role)-> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
