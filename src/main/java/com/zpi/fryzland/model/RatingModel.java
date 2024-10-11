package com.zpi.fryzland.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name="Oceny")
public class RatingModel {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="OcenyID")
    private Integer ratingColumn;
    @Column(name="WartoscOceny")
    private float ratingValue;
    @Column(name="Opinia")
    private String ratingOpinion;
    @ManyToOne
    @JoinColumn(name="PracownikID")
    private EmployeeModel employeeModel;
    @ManyToOne
    @JoinColumn(name="WizytaID")
    private VisitModel visitModel;
}
