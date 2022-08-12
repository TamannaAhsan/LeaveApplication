package com.example.leave_application.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="Role")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationRole {

    @Id
    @SequenceGenerator(
            name = "role_sequence",
            sequenceName = "role_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "role_sequence"
    )

    private Integer roleId;

    @Column(nullable = false)
    private String label;

    @Column(nullable = false)
    private String roleName;
}
