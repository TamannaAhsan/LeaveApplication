package com.example.leave_application.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="Leave_Type")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LeaveType {

    @Id
    @SequenceGenerator(
            name = "leaveType_sequence",
            sequenceName = "leaveType_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "leaveType_sequence"
    )

    private Integer leaveTypeId;

    @Column(nullable = false)
    private String leaveTypeName;

    @Column(nullable = false)
    private String leaveTypeRemark;
}
