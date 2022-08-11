package com.example.leave_application.payload;

import com.example.leave_application.entity.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@NoArgsConstructor
@Getter
@Setter
public class LeaveApplicationFilterDTO {

    private Date fromDate;

    private Date toDate;

    private Integer leaveTypeId;

    private Status status;
}
