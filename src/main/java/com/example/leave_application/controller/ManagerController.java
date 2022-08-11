package com.example.leave_application.controller;

import com.example.leave_application.payload.LeaveApplicationDTO;
import com.example.leave_application.service.LeaveApplicationService;
import com.example.leave_application.service.ManagerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("manager/api/v1/")
@AllArgsConstructor
public class ManagerController {

    private final ManagerService managerService;

    @PostMapping("approved/{leaveApplicationId}")
    public ResponseEntity<LeaveApplicationDTO> approvedLeaveApplication(@RequestBody LeaveApplicationDTO leaveApplicationDTO, @PathVariable("leaveApplicationId") Integer leaveApplicationId){
        LeaveApplicationDTO approveLeaveApplication = this.managerService.approveLeaveApplication(leaveApplicationDTO,leaveApplicationId);
        return ResponseEntity.ok(approveLeaveApplication);
    }

    @PostMapping("rejected/{leaveApplicationId}")
    public ResponseEntity<LeaveApplicationDTO> rejectedLeaveApplication(@RequestBody LeaveApplicationDTO leaveApplicationDTO, @PathVariable("leaveApplicationId") Integer leaveApplicationId){
        LeaveApplicationDTO rejectLeaveApplication = this.managerService.rejectLeaveApplication(leaveApplicationDTO,leaveApplicationId);
        return ResponseEntity.ok(rejectLeaveApplication);
    }

    @PostMapping("remark/{leaveApplicationId}")
    public ResponseEntity<LeaveApplicationDTO> putManagerRemarkLeaveApplication(@RequestBody LeaveApplicationDTO leaveApplicationDTO, @PathVariable("leaveApplicationId") Integer leaveApplicationId){
        LeaveApplicationDTO putManagerRemark = this.managerService.putManagerRemark(leaveApplicationDTO,leaveApplicationId);
        return ResponseEntity.ok(putManagerRemark);
    }
    @GetMapping("/pendingApplications")
    public ResponseEntity<List<LeaveApplicationDTO>> showAllPendingApplication(){
        List<LeaveApplicationDTO> pendingApplication = this.managerService.showAllPendingStatus();
        return ResponseEntity.ok(pendingApplication);
    }

}
