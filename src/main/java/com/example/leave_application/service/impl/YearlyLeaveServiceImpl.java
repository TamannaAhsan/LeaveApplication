package com.example.leave_application.service.impl;

import com.example.leave_application.entity.LeaveType;
import com.example.leave_application.entity.YearlyLeave;
import com.example.leave_application.exception.ResourceNotFoundException;
import com.example.leave_application.payload.YearlyLeaveDTO;
import com.example.leave_application.repository.LeaveTypeRepository;
import com.example.leave_application.repository.YearLyLeaveRepository;
import com.example.leave_application.service.YearlyLeaveService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class YearlyLeaveServiceImpl implements YearlyLeaveService {

    private final YearLyLeaveRepository yearLyLeaveRepository;

    private final LeaveTypeRepository leaveTypeRepository;

    private final ModelMapper modelMapper;


    @Override
    public YearlyLeaveDTO createYearlyLeave(YearlyLeaveDTO yearlyLeaveDTO, Integer leaveTypeId) {
        LeaveType leaveType = this.leaveTypeRepository.findById(leaveTypeId).orElseThrow(()-> new ResourceNotFoundException("Leave Type", "Id",leaveTypeId));
        YearlyLeave yearlyLeave = this.modelMapper.map(yearlyLeaveDTO,YearlyLeave.class);
        yearlyLeave.setYear(yearlyLeaveDTO.getYear());
        yearlyLeave.setMaximumDay(yearlyLeaveDTO.getMaximumDay());
        yearlyLeave.setLeaveType(leaveType);
        YearlyLeave newYearlyLeave = this.yearLyLeaveRepository.save(yearlyLeave);
        return this.modelMapper.map(newYearlyLeave, YearlyLeaveDTO.class);

    }

    @Override
    public void deleteYearlyLeave(Integer yearlyLeaveId) {
        YearlyLeave yearlyLeave = this.yearLyLeaveRepository.findById(yearlyLeaveId).orElseThrow(()-> new ResourceNotFoundException("Yearly Leave", "Id", yearlyLeaveId));
        this.yearLyLeaveRepository.delete(yearlyLeave);
    }

    @Override
    public Set<YearlyLeaveDTO> getAllYearlyLeave() {
        List<YearlyLeave> yearlyLeaves = this.yearLyLeaveRepository.findAll();
        Set<YearlyLeaveDTO> yearlyLeaveDTOS = yearlyLeaves.stream().map((post) -> this.yearlyLeaveToDto(post)).collect(Collectors.toSet());
        return yearlyLeaveDTOS;
    }

    private YearlyLeaveDTO yearlyLeaveToDto (YearlyLeave yearlyLeave){
        YearlyLeaveDTO yearlyLeaveDTO = this.modelMapper.map(yearlyLeave,YearlyLeaveDTO.class);
        return yearlyLeaveDTO;
    }
}
