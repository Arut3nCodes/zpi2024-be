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
@Entity(name="Wizyty")
public class VisitModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="WizytaID")
    private Integer visitID;
    @Column(name="DataW")
    private Date visitDate;
    @Column(name="GodzinaRozp")
    private Time visitStartDate;
    @ManyToOne
    @JoinColumn(name="PracownikID")
    private EmployeeModel employeeModel;
    @ManyToOne
    @JoinColumn(name="KlientID")
    private CustomerModel customerModel;
    @ManyToOne
    @JoinColumn(name="SalonID")
    private SalonModel salonModel;


}
