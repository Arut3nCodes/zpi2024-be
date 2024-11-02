package com.zpi.fryzland.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.hibernate.validator.constraints.Range;

import java.sql.Time;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "GodzinyOtwarcia")
public class OpeningHoursModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GodzID")
    private Integer openingHoursID;
    @Range(min = 1, max = 7)
    @Column(name = "DzienTygodnia", nullable = false)
    private Integer weekday;
    @Column(name = "GodzOtw", nullable = false, columnDefinition = "TIME(0)")
    private Time openingHour;
    @Column(name = "GodzZamk", nullable = false, columnDefinition = "TIME(0)")
    private Time closingHour;
    @ManyToOne()
    @JoinColumn(name = "salonID", nullable = false)
    private SalonModel salonModel;
}
