package com.example.leave_application.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplicationUserDTO {

    private Integer id;

    private String email;

    private String password;

    private String accessToken;

    private Date tokenExpireDate;

    private String username;

    private ApplicationUserDTO manager;

    private Integer roleId;


}