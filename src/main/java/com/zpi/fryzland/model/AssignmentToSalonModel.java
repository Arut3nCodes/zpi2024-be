package com.zpi.fryzland.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name="PrzydzialDoSalonu")
public class AssignmentToSalonModel {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="PrzydzialID")
    private Integer assignmentID;
    @FutureOrPresent
    @Column(name="DataPrzydzialu", nullable = false)
    private LocalDate assignmentDate;
    @ManyToOne
    @JoinColumn(name="SalonID")
    private SalonModel salonModel;
    @ManyToOne
    @JoinColumn(name="PracownikID")
    private EmployeeModel employeeModel;
}
