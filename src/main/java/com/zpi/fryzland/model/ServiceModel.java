package com.zpi.fryzland.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "Usługi")
public class ServiceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "UsługaID")
    private Integer serviceID;
    @Column(name = "NazwaU", nullable = false, length = 50)
    private String serviceName;
    @Range(min = 1, max = 4)
    @Column(name="CzasU", nullable = false)
    private Integer serviceSpan;
    @Range(min = 1, max = 10000)
    @Column(name="CenaU", nullable = false)
    private Float servicePrice;
    @Column(name="OpisU", length = 200)
    private String serviceDescription;
    @ManyToOne
    @JoinColumn(name="KategoriaU", unique = true)
    private ServiceCategoryModel serviceCategoryModel;
}
