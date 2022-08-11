package com.example.leave_application.service.impl;

import com.example.leave_application.entity.LeaveApplication;
import com.example.leave_application.entity.Status;
import com.example.leave_application.exception.ResourceNotFoundException;
import com.example.leave_application.payload.LeaveApplicationDTO;
import com.example.leave_application.repository.LeaveApplicationRepository;
import com.example.leave_application.service.ManagerService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ManagerServiceImpl implements ManagerService {

    private final LeaveApplicationRepository leaveApplicationRepository;
    private final ModelMapper modelMapper;


    @Override
    public LeaveApplicationDTO approveLeaveApplication(LeaveApplicationDTO leaveApplicationDTO, Integer leaveApplicationId) {

        LeaveApplication leaveApplication = this.leaveApplicationRepository.findById(leaveApplicationId)
                .orElseThrow(()-> new ResourceNotFoundException("Leave Application", "Id", leaveApplicationId));

        leaveApplication.setStatus(Status.APPROVED);

        LeaveApplication sendLeaveApplication = this.leaveApplicationRepository.save(leaveApplication);

        return this.modelMapper.map(sendLeaveApplication, LeaveApplicationDTO.class);
    }

    @Override
    public LeaveApplicationDTO rejectLeaveApplication(LeaveApplicationDTO leaveApplicationDTO, Integer leaveApplicationId) {

        LeaveApplication leaveApplication = this.leaveApplicationRepository.findById(leaveApplicationId)
                .orElseThrow(()-> new ResourceNotFoundException("Leave Application", "Id", leaveApplicationId));

        leaveApplication.setStatus(Status.REJECTED);

        LeaveApplication sendLeaveApplication = this.leaveApplicationRepository.save(leaveApplication);

        return this.modelMapper.map(sendLeaveApplication, LeaveApplicationDTO.class);
    }

    @Override
    public LeaveApplicationDTO putManagerRemark(LeaveApplicationDTO leaveApplicationDTO, Integer leaveApplicationId) {

        LeaveApplication leaveApplication = this.leaveApplicationRepository.findById(leaveApplicationId)
                .orElseThrow(()-> new ResourceNotFoundException("Leave Application", "Id", leaveApplicationId));

        leaveApplication.setManagerRemark(leaveApplicationDTO.getManagerRemark());

        LeaveApplication sendLeaveApplication = this.leaveApplicationRepository.save(leaveApplication);

        return this.modelMapper.map(sendLeaveApplication, LeaveApplicationDTO.class);
    }
    public List<LeaveApplicationDTO> showAllPendingStatus() {
        List<LeaveApplication> pendingApplications = this.leaveApplicationRepository.findAll();

        List<LeaveApplication> applications = pendingApplications.stream()
                .filter(application-> application.getStatus() == Status.PENDING)
                .collect(Collectors.toList());

        List<LeaveApplicationDTO> applicationDTOS = applications.stream()
                .map(application -> this.leaveApplicationToDto(application))
                .collect(Collectors.toList());
        return applicationDTOS;
    }

    private LeaveApplicationDTO leaveApplicationToDto (LeaveApplication leaveApplication){
        LeaveApplicationDTO leaveApplicationDTO = this.modelMapper.map(leaveApplication,LeaveApplicationDTO.class);
        return leaveApplicationDTO;
    }
}
