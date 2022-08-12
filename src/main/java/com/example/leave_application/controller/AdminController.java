package com.example.leave_application.controller;

import com.example.leave_application.payload.ApiResponse;
import com.example.leave_application.payload.ApplicationUserDTO;
import com.example.leave_application.payload.LeaveTypeDTO;
import com.example.leave_application.payload.YearlyLeaveDTO;
import com.example.leave_application.service.ApplicationUserService;
import com.example.leave_application.service.LeaveTypeService;
import com.example.leave_application.service.YearlyLeaveService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("api/v1/")
@AllArgsConstructor
public class AdminController {

    private final LeaveTypeService leaveTypeService;
    private final YearlyLeaveService yearlyLeaveService;
    private final ApplicationUserService applicationUserService;

    //Leave Type Controller//

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addLeaveType")
    public ResponseEntity<LeaveTypeDTO> createLeaveType (@Valid @RequestBody LeaveTypeDTO leaveTypeDTO){
        LeaveTypeDTO createLeaveTypeDto = this.leaveTypeService.createLeaveType(leaveTypeDTO);
        return new ResponseEntity<>(createLeaveTypeDto, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{leaveTypeId}")
    public ResponseEntity<LeaveTypeDTO> updateLeaveType(@Valid @RequestBody LeaveTypeDTO leaveTypeDTO, @PathVariable("leaveTypeId") Integer leaveTypeId){
        LeaveTypeDTO updateLeaveType = this.leaveTypeService.updateLeaveType(leaveTypeDTO, leaveTypeId);
        return ResponseEntity.ok(updateLeaveType);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping ("/delete/{leaveTypeId}")
    public ResponseEntity<ApiResponse> deleteLeaveType(@PathVariable("leaveTypeId") Integer leaveTypeId){
        this.leaveTypeService.deleteLeaveType(leaveTypeId);
        return new ResponseEntity(new ApiResponse("Leave Type deleted successfully",true),HttpStatus.OK);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/allLeaveType")
    public ResponseEntity<Set<LeaveTypeDTO>> getAllLeaveType(){
        return ResponseEntity.ok(this.leaveTypeService.getAllType());
    }

    //Yearly Leave Controller//

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/leaveType/{leaveTypeId}/yearlyLeave")
    public ResponseEntity<YearlyLeaveDTO> allocateYearlyLeave (@RequestBody YearlyLeaveDTO yearlyLeaveDTO, @PathVariable Integer leaveTypeId){
        YearlyLeaveDTO yearlyLeave = this.yearlyLeaveService.createYearlyLeave(yearlyLeaveDTO,leaveTypeId);
        return new ResponseEntity(yearlyLeave,HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/allYearlyLeave")
    public ResponseEntity<Set<YearlyLeaveDTO>> getAllAllocateYearlyLeave(){
        return ResponseEntity.ok(this.yearlyLeaveService.getAllYearlyLeave());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping ("yearlyLeave/delete/{yearlyLeaveId}")
    public ResponseEntity<ApiResponse> deleteAllocateYearlyLeave(@PathVariable("yearlyLeaveId") Integer yearlyLeaveId){
        this.yearlyLeaveService.deleteYearlyLeave(yearlyLeaveId);
        return new ResponseEntity(new ApiResponse("Allocated Yearly Leave deleted successfully",true),HttpStatus.OK);

    }

    // Application User Controller//

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping ("/addUser")
    public ResponseEntity<ApplicationUserDTO> createUser (@RequestBody ApplicationUserDTO applicationUserDTO){
        ApplicationUserDTO createUserDto = this.applicationUserService.createUser(applicationUserDTO);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping ("application/delete/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer userId){
        this.applicationUserService.deleteUser(userId);
        return new ResponseEntity(new ApiResponse("User deleted successfully",true),HttpStatus.OK);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("applicationUser/allUser")
    public ResponseEntity<Set<ApplicationUserDTO>> getAllUser(){
        return ResponseEntity.ok(this.applicationUserService.getALLUsers());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/user/{managerId}/managerUser")
    public ResponseEntity<ApplicationUserDTO> createManagerUser (@RequestBody ApplicationUserDTO applicationUserDTO, @PathVariable Integer managerId){
        ApplicationUserDTO applicationUserDTO1 = this.applicationUserService.createManagerUser(applicationUserDTO,managerId);
        return new ResponseEntity(applicationUserDTO1,HttpStatus.CREATED);
    }
}
