package com.zpi.fryzland.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "Usługi")
public class ServiceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "UsługaID")
    private Integer serviceID;
    @Column(name = "NazwaU")
    private String serviceName;
    @Column(name="CzasU")
    private Integer serviceSpan;
    @Column(name="CenaU")
    private float servicePrice;
    @Column(name="OpisU")
    private String serviceDescription;
//    @ManyToOne
//    @JoinColumn(name="KategoriaU")
//    private ServiceCategoryModel serviceCategoryModel;
}
