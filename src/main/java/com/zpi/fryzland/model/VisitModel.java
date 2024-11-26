package com.zpi.fryzland.model;

import com.zpi.fryzland.model.enums.VisitStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Builder
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
    private LocalDate visitDate;
    @FutureOrPresent
    @Column(name="GodzinaRozp", nullable = false, columnDefinition = "TIME(0)")
    private LocalTime visitStartDate;
    @ColumnDefault("'RESERVED'")
    @Enumerated(EnumType.STRING)
    @Column(name="StatusW", nullable = false)
    private VisitStatus visitStatus;
    @ManyToOne
    @JoinColumn(name="PrzydziałDoSalonuID")
    private AssignmentToSalonModel assigmentModel;
    @ManyToOne
    @JoinColumn(name="KlientID")
    private CustomerModel customerModel;
}
