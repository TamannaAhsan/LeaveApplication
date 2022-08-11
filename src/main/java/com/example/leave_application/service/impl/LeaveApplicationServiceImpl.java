package com.example.leave_application.service.impl;

import com.example.leave_application.entity.ApplicationUser;
import com.example.leave_application.entity.LeaveApplication;
import com.example.leave_application.entity.LeaveType;
import com.example.leave_application.entity.Status;
import com.example.leave_application.exception.ResourceNotFoundException;
import com.example.leave_application.payload.LeaveApplicationDTO;
import com.example.leave_application.payload.LeaveApplicationFilterDTO;
import com.example.leave_application.repository.ApplicationUserRepository;
import com.example.leave_application.repository.LeaveApplicationRepository;
import com.example.leave_application.repository.LeaveTypeRepository;
import com.example.leave_application.service.LeaveApplicationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LeaveApplicationServiceImpl implements LeaveApplicationService {

    private final LeaveApplicationRepository leaveApplicationRepository;

    private final ModelMapper modelMapper;

    private final ApplicationUserRepository applicationUserRepository;

    private final LeaveTypeRepository leaveTypeRepository;

    @Override
    public LeaveApplicationDTO createLeaveApplication(LeaveApplicationDTO leaveApplicationDTO, Integer userId, Integer leaveTypeId) {

        ApplicationUser applicationUser = this.applicationUserRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
        LeaveType leaveType = this.leaveTypeRepository.findById(leaveTypeId)
                .orElseThrow(()-> new ResourceNotFoundException("Leave Type", "Id",leaveTypeId));

        LeaveApplication leaveApplication = this.dtoToLeaveApplication(leaveApplicationDTO);
        leaveApplication.setFromDate(leaveApplicationDTO.getFromDate());
        leaveApplication.setToDate(leaveApplicationDTO.getToDate());
        leaveApplication.setLeaveType(leaveType);
        leaveApplication.setRemark(leaveApplicationDTO.getRemark());
        leaveApplication.setApplicationUser(applicationUser);

        LeaveApplication newApplication = this.leaveApplicationRepository.save(leaveApplication);
        return this.modelMapper.map(newApplication, LeaveApplicationDTO.class);
    }

    @Override
    public void deleteLeaveApplication(Integer leaveApplicationId) {
        LeaveApplication leaveApplication = this.leaveApplicationRepository.findById(leaveApplicationId)
                .orElseThrow(()-> new ResourceNotFoundException("Leave Application", "Id", leaveApplicationId));
        this.leaveApplicationRepository.delete(leaveApplication);

    }

    @Override
    public Set<LeaveApplicationDTO> getAllLeaveApplication() {
        List<LeaveApplication> leaveApplications = this.leaveApplicationRepository.findAll();
        Set<LeaveApplicationDTO> leaveApplicationDTOS = leaveApplications.stream().map((post) -> this.leaveApplicationToDto(post)).collect(Collectors.toSet());
        return leaveApplicationDTOS;

    }

    @Override
    public LeaveApplicationDTO updateLeaveApplication(LeaveApplicationDTO leaveApplicationDTO, Integer leaveApplicationId) {

        LeaveApplication leaveApplication = this.leaveApplicationRepository.findById(leaveApplicationId)
                .orElseThrow(()-> new ResourceNotFoundException("Leave Application", "Id", leaveApplicationId));

        leaveApplication.setFromDate(leaveApplicationDTO.getFromDate());
        leaveApplication.setToDate(leaveApplicationDTO.getToDate());
        leaveApplication.setRemark(leaveApplicationDTO.getRemark());

        LeaveApplication updatedLeaveApplication = this.leaveApplicationRepository.save(leaveApplication);

        return this.modelMapper.map(updatedLeaveApplication, LeaveApplicationDTO.class);
    }

    @Override
    public LeaveApplicationDTO sendLeaveApplication(LeaveApplicationDTO leaveApplicationDTO, Integer leaveApplicationId) {

        LeaveApplication leaveApplication = this.leaveApplicationRepository.findById(leaveApplicationId)
                .orElseThrow(()-> new ResourceNotFoundException("Leave Application", "Id", leaveApplicationId));

        leaveApplication.setStatus(Status.PENDING);

        LeaveApplication sendLeaveApplication = this.leaveApplicationRepository.save(leaveApplication);

        return this.modelMapper.map(sendLeaveApplication, LeaveApplicationDTO.class);

    }

    @Override
    public List<LeaveApplicationDTO> showAllLeaveByFilter(LeaveApplicationFilterDTO leaveApplicationFilterDTO, Integer userId) {

        List<LeaveApplication> leaveApplications = this.leaveApplicationRepository.findLeaveApplicationByApplicationUserId(userId);

        // By Status //
        if(leaveApplicationFilterDTO.getStatus()!= null){
           leaveApplications = leaveApplications.stream()
                    .filter(applicationLeave -> applicationLeave.getStatus() == leaveApplicationFilterDTO.getStatus())
                    .collect(Collectors.toList());
        }

        // By LeaveType //
        if(leaveApplicationFilterDTO.getLeaveTypeId()!=null){
            LeaveType leaveType = leaveTypeRepository.findById(leaveApplicationFilterDTO.getLeaveTypeId())
                    .orElseThrow(()-> new ResourceNotFoundException("Leave Type", "Id", leaveApplicationFilterDTO.getLeaveTypeId()));
            leaveApplications = leaveApplications.stream()
                    .filter(applicationLeave -> applicationLeave.getLeaveType().equals(leaveType))
                    .collect(Collectors.toList());
        }

        List<LeaveApplicationDTO> applicationDTOS = leaveApplications.stream()
                .map(application -> this.leaveApplicationToDto(application))
                .collect(Collectors.toList());
        return applicationDTOS;

    }

    private LeaveApplication dtoToLeaveApplication (LeaveApplicationDTO leaveApplicationDTO){
        LeaveApplication leaveApplication = this.modelMapper.map(leaveApplicationDTO,LeaveApplication.class);
        return leaveApplication;
    }

    private LeaveApplicationDTO leaveApplicationToDto (LeaveApplication leaveApplication){
        LeaveApplicationDTO leaveApplicationDTO = this.modelMapper.map(leaveApplication,LeaveApplicationDTO.class);
        return leaveApplicationDTO;
    }

}
