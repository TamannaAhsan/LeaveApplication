package com.example.leave_application.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class LeaveTypeDTO {

    private Integer leaveTypeId;

    @NotEmpty
    @Size(min = 4, message = "Leave Type must be minimum of 4 characters")
    private String leaveTypeName;

    @NotEmpty
    private String leaveTypeRemark;
}