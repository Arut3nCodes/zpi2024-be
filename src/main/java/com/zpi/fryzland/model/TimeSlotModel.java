package com.zpi.fryzland.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.PastOrPresent;
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
    @FutureOrPresent
    @Column(name="DataO", nullable = false)
    private Date timeSlotDate;
    @Id
    @FutureOrPresent
    @Column(name="GodzinaO", nullable = false)
    private Time timeSlotTime;
    @ManyToOne
    @JoinColumn(name="PracownikID", unique = true)
    private EmployeeModel employeeModel;
}
