package com.example.leave_application.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class YearlyLeaveDTO {

    private Integer yearlyLeaveId;

    private Integer year;

    private Integer maximumDay;

    private LeaveTypeDTO leaveType;

}
