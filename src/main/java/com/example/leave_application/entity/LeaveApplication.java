package com.example.leave_application.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "leave_application")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LeaveApplication {

    @Id
    @SequenceGenerator(
            name = "leaveApplication_sequence",
            sequenceName = "leaveApplication_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "leaveApplication_sequence"
    )
    private Integer id;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private Date fromDate;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private Date toDate;

    @ManyToOne
    @JoinColumn(
            name = "leaveType_id",
            referencedColumnName = "leaveTypeId"
    )
    private LeaveType leaveType;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String remark;

    private String managerRemark;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id"
    )
    private ApplicationUser applicationUser;

}
