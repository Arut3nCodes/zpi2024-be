package com.zpi.fryzland.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.sql.Time;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "GodzinyOtwarcia")
public class OpeningHoursModel {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "GodzID")
    private Integer openingHoursID;
    @Column(name = "DzienTygodnia")
    private Integer weekday;
    @Column(name = "GodzOtw")
    private Time openingHour;
    @Column(name = "GodzZamk")
    private Time closingHour;
    @ManyToOne()
    @JoinColumn(name = "salonID")
    private Integer salonID;
}
