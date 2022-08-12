package com.example.leave_application.payload;

import com.example.leave_application.entity.ApplicationUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ApplicationUserDTO {

    private Integer id;

    private String email;

    private String password;

    //private String accessToken;

    //private Date tokenExpireDate;

    private String username;

    private ApplicationUserDTO manager;
}