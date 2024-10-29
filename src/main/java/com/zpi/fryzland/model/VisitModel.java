package com.zpi.fryzland.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name="Wizyty")
public class VisitModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="WizytaID")
    private Integer visitID;
    @FutureOrPresent
    @Column(name="DataW", nullable = false)
    private Date visitDate;
    @FutureOrPresent
    @Column(name="GodzinaRozp", nullable = false, columnDefinition = "TIME(0)")
    private Time visitStartDate;
    @ManyToOne
    @JoinColumn(name="PracownikID")
    private AssignmentToSalonModel assigmentModel;
    @ManyToOne
    @JoinColumn(name="KlientID")
    private CustomerModel customerModel;
}
