package com.example.leave_application.controller;

import com.example.leave_application.payload.ApplicationUserDTO;
import com.example.leave_application.service.ApplicationUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("controller/api/v1/")
@AllArgsConstructor
public class ApplicationUserController {

    private final ApplicationUserService applicationUserService;

    @PutMapping("/update/{userId}")
    public ResponseEntity<ApplicationUserDTO> updatePassword(@RequestBody ApplicationUserDTO applicationUserDTO, @PathVariable("userId") Integer userId){
        ApplicationUserDTO updatedPassword = this.applicationUserService.updatePassword(applicationUserDTO, userId);
        return ResponseEntity.ok(updatedPassword);

    }

}
