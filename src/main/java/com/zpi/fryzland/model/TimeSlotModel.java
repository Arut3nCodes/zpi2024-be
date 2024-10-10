package com.zpi.fryzland.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name="OkienkaCzasowe")
public class TimeSlotModel {
    @Id
    @Column(name="DataO")
    private Date timeSlotDate;
    @Id
    @Column(name="GodzinaO")
    private Time timeSlotTime;
    @ManyToOne
    @JoinColumn(name="PracownikID")
    private EmployeeModel employeeModel;
}
