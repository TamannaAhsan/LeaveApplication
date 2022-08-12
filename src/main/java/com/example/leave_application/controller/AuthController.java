package com.example.leave_application.controller;

import com.example.leave_application.auth.JwtTokenHelper;
import com.example.leave_application.exception.ApiException;
import com.example.leave_application.payload.JwtAuthRequest;
import com.example.leave_application.payload.JwtAuthResponse;
import com.example.leave_application.service.ApplicationUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth/api/v1/")
@AllArgsConstructor
public class AuthController {

    //private final ApplicationUserService applicationUserService;
    private final JwtTokenHelper jwtTokenHelper;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken (@RequestBody JwtAuthRequest request) throws Exception {
        this.authenticate(request.getUsername(), request.getPassword());
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
        String token = this.jwtTokenHelper.generateToken(userDetails);
        JwtAuthResponse response = new JwtAuthResponse();
        response.setToken(token);
        return new ResponseEntity(response, HttpStatus.OK);

    }

    private void authenticate(String username, String password) throws Exception {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);

        try{
            this.authenticationManager.authenticate(authenticationToken);
        }catch (BadCredentialsException ex){
            System.out.println(("Invalid Details!!"));
            throw new ApiException("Invalid username or password");
        }
    }

}
