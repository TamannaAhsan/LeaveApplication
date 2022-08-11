package com.example.leave_application.controller;

import com.example.leave_application.payload.ApiResponse;
import com.example.leave_application.payload.ApplicationUserDTO;
import com.example.leave_application.payload.LeaveApplicationDTO;
import com.example.leave_application.payload.LeaveApplicationFilterDTO;
import com.example.leave_application.service.ApplicationUserService;
import com.example.leave_application.service.LeaveApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("controller/api/v1/")
@AllArgsConstructor
public class ApplicationUserController {

    private final ApplicationUserService applicationUserService;

    private final LeaveApplicationService leaveApplicationService;

    // Application User Controller //
    @PutMapping("/update/{userId}")
    public ResponseEntity<ApplicationUserDTO> updatePassword(@RequestBody ApplicationUserDTO applicationUserDTO, @PathVariable("userId") Integer userId){
        ApplicationUserDTO updatedPassword = this.applicationUserService.updatePassword(applicationUserDTO, userId);
        return ResponseEntity.ok(updatedPassword);

    }

    // Leave Application Controller //

    @PostMapping("/user/{userId}/leaveType/{leaveTypeId}/leaveApplication")
    public ResponseEntity<LeaveApplicationDTO> createLeaveApplications ( @RequestBody LeaveApplicationDTO leaveApplicationDTO, @PathVariable Integer userId, @PathVariable Integer leaveTypeId){
        LeaveApplicationDTO createLeaveApplication = this.leaveApplicationService.createLeaveApplication(leaveApplicationDTO,userId,leaveTypeId);
        return new ResponseEntity(createLeaveApplication,HttpStatus.CREATED);
    }

    @GetMapping("/leaveApplications")
    public ResponseEntity<Set<LeaveApplicationDTO>> getAllLeaveApplication(){

        return ResponseEntity.ok(this.leaveApplicationService.getAllLeaveApplication());
    }

    @DeleteMapping ("/delete/{leaveApplicationId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("leaveApplicationId") Integer leaveApplicationId){
        this.leaveApplicationService.deleteLeaveApplication(leaveApplicationId);
        return new ResponseEntity(new ApiResponse("Leave Application deleted successfully",true), HttpStatus.OK);

    }

    @PutMapping("leaveApplication/update/{leaveApplicationId}")
    public ResponseEntity<LeaveApplicationDTO> updateLeaveApplication(@RequestBody LeaveApplicationDTO leaveApplicationDTO, @PathVariable("leaveApplicationId") Integer leaveApplicationId){
        LeaveApplicationDTO updatedLeaveApplication = this.leaveApplicationService.updateLeaveApplication(leaveApplicationDTO,leaveApplicationId);
        return ResponseEntity.ok(updatedLeaveApplication);
    }

    @PostMapping("application/send/{leaveApplicationId}")
    public ResponseEntity<LeaveApplicationDTO> sendLeaveApplication(@RequestBody LeaveApplicationDTO leaveApplicationDTO, @PathVariable("leaveApplicationId") Integer leaveApplicationId){
        LeaveApplicationDTO sendLeaveApplication = this.leaveApplicationService.sendLeaveApplication(leaveApplicationDTO,leaveApplicationId);
        return ResponseEntity.ok(sendLeaveApplication);
    }
    @PostMapping ("/showFilter/{userId}")
    public ResponseEntity<List<LeaveApplicationDTO>> showAllLeaveByFilter(@RequestBody LeaveApplicationFilterDTO leaveApplicationFilterDTO, @PathVariable("userId") Integer userId){
        List<LeaveApplicationDTO> showFilter = this.leaveApplicationService.showAllLeaveByFilter(leaveApplicationFilterDTO, userId);
        return ResponseEntity.ok(showFilter);
    }
}
