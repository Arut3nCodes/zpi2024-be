package com.zpi.fryzland.model;

import com.zpi.fryzland.model.compositekey.TimeSlotKey;
import com.zpi.fryzland.validators.FutureOrPresentDateAndTime;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name="OkienkaCzasowe")
@IdClass(TimeSlotKey.class)
@FutureOrPresentDateAndTime
public class TimeSlotModel {
    @Id
    @Column(name="DataO", nullable = false)
    private LocalDate timeSlotDate;
    @Id
    @Column(name="GodzinaO", nullable = false)
    private LocalTime timeSlotTime;
    @Id
    @ManyToOne
    @JoinColumn(name="PracownikID", nullable = false)
    private EmployeeModel employeeModel;
}
