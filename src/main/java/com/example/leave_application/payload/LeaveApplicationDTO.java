package com.example.leave_application.payload;

import com.example.leave_application.entity.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@NoArgsConstructor
@Getter
@Setter
public class LeaveApplicationDTO {

    private Integer id;

    private Date fromDate;

    private Date toDate;

    private LeaveTypeDTO leaveType;

    private Status status;

    private String remark;

    private String managerRemark;

    private ApplicationUserDTO applicationUser;

}
