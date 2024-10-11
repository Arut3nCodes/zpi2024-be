package com.zpi.fryzland.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name="PrzydzialDoSalonu")
public class AssigmentToSalonModel {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="PrzydzialID")
    private Integer assigmentID;
    @Column(name="DataPrzydzialu")
    private Date assigmentDate;
    @ManyToOne
    @JoinColumn(name="SalonID")
    private SalonModel salonModel;
    @ManyToOne
    @JoinColumn(name="PracownikID")
    private EmployeeModel employeeModel;
}
