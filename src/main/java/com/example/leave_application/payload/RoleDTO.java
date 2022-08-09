package com.example.leave_application.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RoleDTO {

    private Integer roleId;

    private String label;

    private String roleName;
}
