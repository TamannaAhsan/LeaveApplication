package com.example.leave_application.repository;

import com.example.leave_application.entity.ApplicationUser;
import com.example.leave_application.payload.ApplicationUserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class ApplicationUserRepositoryTest {

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Test
    public void createManager(){
        Integer Manager = 1;
        ApplicationUser user = this.applicationUserRepository.findById(1).orElse(null);
        System.out.println(user.getId());
    }

}