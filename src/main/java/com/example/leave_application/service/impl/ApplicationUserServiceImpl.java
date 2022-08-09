package com.example.leave_application.service.impl;

import com.example.leave_application.entity.ApplicationUser;

import com.example.leave_application.exception.ResourceNotFoundException;
import com.example.leave_application.payload.ApplicationUserDTO;
import com.example.leave_application.repository.ApplicationUserRepository;
import com.example.leave_application.service.ApplicationUserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ApplicationUserServiceImpl implements ApplicationUserService {

    private final ApplicationUserRepository applicationUserRepository;
    private final ModelMapper modelMapper;

    @Override
    public ApplicationUserDTO createUser(ApplicationUserDTO userDTO) {
        ApplicationUser applicationUser = this.dtoToApplicationUser(userDTO);
        ApplicationUser savedUser = this.applicationUserRepository.save(applicationUser);
        return this.ApplicationUserToDto(savedUser);
    }
    @Override
    public ApplicationUserDTO createManagerUser(ApplicationUserDTO userDTO, Integer managerId) {

        ApplicationUser applicationManagerUser = this.applicationUserRepository.findById(managerId).orElseThrow(()-> new ResourceNotFoundException("Manager", "Id",managerId));

        ApplicationUser applicationUser = this.dtoToApplicationUser(userDTO);
        applicationUser.setEmail(userDTO.getEmail());
        applicationUser.setPassword(userDTO.getPassword());
        applicationUser.setUsername(userDTO.getUsername());
        applicationUser.setManager(applicationManagerUser);
        ApplicationUser savedUser = this.applicationUserRepository.save(applicationUser);
        return this.ApplicationUserToDto(savedUser);
    }

    @Override
    public void deleteUser(Integer userId) {
        ApplicationUser applicationUser = this.applicationUserRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
        this.applicationUserRepository.delete(applicationUser);

    }

    @Override
    public Set<ApplicationUserDTO> getALLUsers() {
        List<ApplicationUser> applicationUsers = this.applicationUserRepository.findAll();
        Set<ApplicationUserDTO> applicationUserDTOS = applicationUsers.stream().map(applicationUser -> this.ApplicationUserToDto(applicationUser)).collect(Collectors.toSet());
        return applicationUserDTOS;
    }

    @Override
    public ApplicationUserDTO updatePassword(ApplicationUserDTO userDTO, Integer userId) {
        ApplicationUser applicationUser = this.applicationUserRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
        applicationUser.setPassword(userDTO.getPassword());
        ApplicationUser updatedPassword = this.applicationUserRepository.save(applicationUser);
        return this.ApplicationUserToDto(updatedPassword);
    }



    private ApplicationUser dtoToApplicationUser (ApplicationUserDTO applicationUserDTO){
        ApplicationUser applicationUser = this.modelMapper.map(applicationUserDTO,ApplicationUser.class);
        return applicationUser;
    }

    private ApplicationUserDTO ApplicationUserToDto (ApplicationUser applicationUser){
        ApplicationUserDTO applicationUserDTO = this.modelMapper.map(applicationUser,ApplicationUserDTO.class);
        return applicationUserDTO;
    }
}
