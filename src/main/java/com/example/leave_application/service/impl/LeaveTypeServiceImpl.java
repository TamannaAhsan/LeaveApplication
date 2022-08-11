package com.example.leave_application.service.impl;
import com.example.leave_application.entity.LeaveType;
import com.example.leave_application.exception.ResourceNotFoundException;
import com.example.leave_application.payload.LeaveTypeDTO;
import com.example.leave_application.repository.LeaveTypeRepository;
import com.example.leave_application.service.LeaveTypeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class LeaveTypeServiceImpl implements LeaveTypeService {

    private final LeaveTypeRepository leaveTypeRepository;
    private final ModelMapper modelMapper;


    @Override
    public LeaveTypeDTO createLeaveType(LeaveTypeDTO leaveTypeDTO) {
        LeaveType leaveType = this.dtoToLeaveType(leaveTypeDTO);
        LeaveType savedLeaveType = this.leaveTypeRepository.save(leaveType);
        return this.leaveTypeToDto(savedLeaveType);
    }

    @Override
    public LeaveTypeDTO updateLeaveType(LeaveTypeDTO leaveTypeDTO, Integer leaveTypeId) {
        LeaveType leaveType = this.leaveTypeRepository.findById(leaveTypeId)
                .orElseThrow(()-> new ResourceNotFoundException("LeaveType", "Id", leaveTypeId));

        leaveType.setLeaveTypeName(leaveTypeDTO.getLeaveTypeName());
        leaveType.setLeaveTypeRemark(leaveTypeDTO.getLeaveTypeRemark());
        LeaveType updatedLeaveType = this.leaveTypeRepository.save(leaveType);
        return this.leaveTypeToDto(updatedLeaveType);
    }

    @Override
    public Set<LeaveTypeDTO> getAllType() {
        List<LeaveType> leaveTypes = this.leaveTypeRepository.findAll();
        Set<LeaveTypeDTO> leaveTypeDTOS = leaveTypes.stream()
                .map(leaveType -> this.leaveTypeToDto(leaveType))
                .collect(Collectors.toSet());
        return leaveTypeDTOS;
    }

    @Override
    public void deleteLeaveType(Integer leaveTypeId) {
        LeaveType leaveType = this.leaveTypeRepository.findById(leaveTypeId)
                .orElseThrow(()-> new ResourceNotFoundException("LeaveType", "Id", leaveTypeId));
        this.leaveTypeRepository.delete(leaveType);
    }

    private LeaveType dtoToLeaveType (LeaveTypeDTO leaveTypeDTO){
        LeaveType leaveType = this.modelMapper.map(leaveTypeDTO,LeaveType.class);
        return leaveType;
    }

    private LeaveTypeDTO leaveTypeToDto (LeaveType leaveType){
        LeaveTypeDTO leaveTypeDTO = this.modelMapper.map(leaveType,LeaveTypeDTO.class);
        return leaveTypeDTO;
    }
}
