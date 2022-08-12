package com.example.leave_application.payload;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LeaveBalanceDTO {
    private int leaveTypeId;
    private int userId;
    private int totalLeave;

    private String userName;
    private String leaveTypeName;
    private int maxAllowedLeave;
}
