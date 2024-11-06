package com.zpi.fryzland.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name="UslugiWybraneDoWizyty")
public class ServicesIncludedInTheVisitModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="UslugaWizytaID")
    private Integer serviceInVisitId;
    @ManyToOne
    @JoinColumn(name="UslugaID")
    private ServiceModel serviceModel;
    @ManyToOne
    @JoinColumn(name="WizytaID")
    private VisitModel visitModel;
}
