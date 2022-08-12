package com.example.leave_application.auth;

import com.example.leave_application.entity.ApplicationUser;
import com.example.leave_application.exception.ResourceNotFoundAuth;
import com.example.leave_application.repository.ApplicationUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ApplicationUserDetailsService implements UserDetailsService {

    private final ApplicationUserRepository applicationUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //loading user from database from username
        ApplicationUser applicationUser = this.applicationUserRepository.findByEmail(username)
                .orElseThrow(()-> new ResourceNotFoundAuth("User", "email", username));
        return applicationUser;
    }
}
