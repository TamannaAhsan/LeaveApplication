package com.example.leave_application;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.util.List;

@SpringBootApplication
public class LeaveApplication implements CommandLineRunner {

    public static void main(String[] args) {

        SpringApplication.run(LeaveApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Override
    public void run(String... args) throws Exception {
        try {

           /* ApplicationRole role = new ApplicationRole();
            role.setLabel("Admin Manager");
            role.setRoleName("ROLE_ADMIN");

            ApplicationRole role1 = new ApplicationRole();
            role1.setLabel("Employee");
            role1.setRoleName("ROLE_NORMAL");

            ApplicationRole role2 = new ApplicationRole();
            role2.setLabel("Manager Administration");
            role2.setRoleName("ROLE_MANAGER");

            List<ApplicationRole> roles = List.of(role, role1, role2);

            List<ApplicationRole> result = this.roleRepository.saveAll(roles);

            result.forEach(r -> {
                System.out.println(r.getRoleName());
            });*/

        } catch (Exception e) {

            e.printStackTrace();
        }

    }
}

