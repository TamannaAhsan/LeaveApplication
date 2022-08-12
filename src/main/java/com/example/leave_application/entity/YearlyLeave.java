package com.example.leave_application.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="Yearly_Leave")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class YearlyLeave {

    @Id
    @SequenceGenerator(
            name = "yearlyLeave_sequence",
            sequenceName = "yearlyLeave_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "yearlyLeave_sequence"
    )

    private Integer yearlyLeaveId;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private Integer maximumDay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "leaveType_Id",
            referencedColumnName = "leaveTypeId"
    )
    private LeaveType leaveType;

}