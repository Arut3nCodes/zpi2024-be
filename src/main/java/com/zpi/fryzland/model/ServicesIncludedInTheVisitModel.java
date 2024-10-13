package com.zpi.fryzland.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name="UsługiWybraneDoWizyty")
public class ServicesIncludedInTheVisitModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="UsługaWizytaID")
    private Integer serviceInVisitId;
    @ManyToOne
    @JoinColumn(name="UsługaID")
    private ServiceModel serviceModel;
    @ManyToOne
    @JoinColumn(name="WizytaID")
    private VisitModel visitModel;
}
