package com.zpi.fryzland.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name="Oceny")
public class RatingModel {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="OcenyID")
    private Integer ratingID;
    @Range(min = 1, max = 5)
    @Column(name="WartoscOceny", nullable = false)
    private float ratingValue;
    @Column(name="Opinia", length = 400)
    private String ratingOpinion;
    @ManyToOne
    @JoinColumn(name="PracownikID", nullable = false)
    private EmployeeModel employeeModel;
    @ManyToOne
    @JoinColumn(name="WizytaID", nullable = false, unique = true)
    private VisitModel visitModel;
}
