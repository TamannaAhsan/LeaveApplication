package com.example.leave_application.service;

import com.example.leave_application.payload.ApplicationUserDTO;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface ApplicationUserService {

    ApplicationUserDTO registerNewUser(ApplicationUserDTO userDTO);
    ApplicationUserDTO createUser(ApplicationUserDTO userDTO);
    ApplicationUserDTO createManagerUser(ApplicationUserDTO userDTO, Integer managerId);
    void deleteUser(Integer userId);
    Set<ApplicationUserDTO> getALLUsers();
    ApplicationUserDTO updatePassword(ApplicationUserDTO userDTO, Integer userId);


}
