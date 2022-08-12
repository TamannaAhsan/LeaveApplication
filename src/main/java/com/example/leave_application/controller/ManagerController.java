package com.example.leave_application.controller;

import com.example.leave_application.payload.LeaveApplicationDTO;
import com.example.leave_application.service.ManagerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("manager/api/v1/")
@AllArgsConstructor
public class ManagerController {

    private final ManagerService managerService;

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("approved/{leaveApplicationId}")
    public ResponseEntity<LeaveApplicationDTO> approvedLeaveApplication(@RequestBody LeaveApplicationDTO leaveApplicationDTO, @PathVariable("leaveApplicationId") Integer leaveApplicationId){
        LeaveApplicationDTO approveLeaveApplication = this.managerService.approveLeaveApplication(leaveApplicationDTO,leaveApplicationId);
        //return ResponseEntity.status(HttpStatus.OK).body(leaveApplicationDTO);
        return ResponseEntity.ok(approveLeaveApplication);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("rejected/{leaveApplicationId}")
    public ResponseEntity<LeaveApplicationDTO> rejectedLeaveApplication(@RequestBody LeaveApplicationDTO leaveApplicationDTO, @PathVariable("leaveApplicationId") Integer leaveApplicationId){
        LeaveApplicationDTO rejectLeaveApplication = this.managerService.rejectLeaveApplication(leaveApplicationDTO,leaveApplicationId);
        return ResponseEntity.ok(rejectLeaveApplication);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("remark/{leaveApplicationId}")
    public ResponseEntity<LeaveApplicationDTO> putManagerRemarkLeaveApplication(@RequestBody LeaveApplicationDTO leaveApplicationDTO, @PathVariable("leaveApplicationId") Integer leaveApplicationId){
        LeaveApplicationDTO putManagerRemark = this.managerService.putManagerRemark(leaveApplicationDTO,leaveApplicationId);
        return ResponseEntity.ok(putManagerRemark);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/pendingApplications")
    public ResponseEntity<List<LeaveApplicationDTO>> showAllPendingApplication(){
        List<LeaveApplicationDTO> pendingApplication = this.managerService.showAllPendingStatus();
        return ResponseEntity.ok(pendingApplication);
    }

}
