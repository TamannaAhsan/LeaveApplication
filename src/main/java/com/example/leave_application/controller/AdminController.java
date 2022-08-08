package com.example.leave_application.controller;

import com.example.leave_application.payload.ApiResponse;
import com.example.leave_application.payload.LeaveTypeDTO;
import com.example.leave_application.payload.YearlyLeaveDTO;
import com.example.leave_application.service.LeaveTypeService;
import com.example.leave_application.service.YearlyLeaveService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("api/v1/")
@AllArgsConstructor
public class AdminController {

    private final LeaveTypeService leaveTypeService;
    private final YearlyLeaveService yearlyLeaveService;

    //Leave Type CRUD//

    @PostMapping("/addLeaveType")
    public ResponseEntity<LeaveTypeDTO> createLeaveType (@Valid @RequestBody LeaveTypeDTO leaveTypeDTO){
        LeaveTypeDTO createLeaveTypeDto = this.leaveTypeService.createLeaveType(leaveTypeDTO);
        return new ResponseEntity<>(createLeaveTypeDto, HttpStatus.CREATED);
    }

    @PutMapping("/update/{leaveTypeId}")
    public ResponseEntity<LeaveTypeDTO> updateLeaveType(@Valid @RequestBody LeaveTypeDTO leaveTypeDTO, @PathVariable("leaveTypeId") Integer leaveTypeId){
        LeaveTypeDTO updateLeaveType = this.leaveTypeService.updateLeaveType(leaveTypeDTO, leaveTypeId);
        return ResponseEntity.ok(updateLeaveType);

    }

    @DeleteMapping ("/delete/{leaveTypeId}")
    public ResponseEntity<ApiResponse> deleteLeaveType(@PathVariable("leaveTypeId") Integer leaveTypeId){
        this.leaveTypeService.deleteLeaveType(leaveTypeId);
        return new ResponseEntity(new ApiResponse("Leave Type deleted successfully",true),HttpStatus.OK);

    }

    @GetMapping("/allLeaveType")
    public ResponseEntity<Set<LeaveTypeDTO>> getAllLeaveType(){
        return ResponseEntity.ok(this.leaveTypeService.getAllType());
    }

    //Yearly Leave//

    @PostMapping("/leaveType/{leaveTypeId}/yearlyLeave")
    public ResponseEntity<YearlyLeaveDTO> allocateYearlyLeave (@RequestBody YearlyLeaveDTO yearlyLeaveDTO, @PathVariable Integer leaveTypeId){
        YearlyLeaveDTO yearlyLeave = this.yearlyLeaveService.createYearlyLeave(yearlyLeaveDTO,leaveTypeId);
        return new ResponseEntity(yearlyLeave,HttpStatus.CREATED);
    }

    @GetMapping("/allYearlyLeave")
    public ResponseEntity<Set<YearlyLeaveDTO>> getAllAllocateYearlyLeave(){
        return ResponseEntity.ok(this.yearlyLeaveService.getAllYearlyLeave());
    }

    @DeleteMapping ("yearlyLeave/delete/{yearlyLeaveId}")
    public ResponseEntity<ApiResponse> deleteAllocateYearlyLeave(@PathVariable("yearlyLeaveId") Integer yearlyLeaveId){
        this.yearlyLeaveService.deleteYearlyLeave(yearlyLeaveId);
        return new ResponseEntity(new ApiResponse("Allocated Yearly Leave deleted successfully",true),HttpStatus.OK);

    }

}
