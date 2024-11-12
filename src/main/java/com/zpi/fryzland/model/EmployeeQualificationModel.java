package com.zpi.fryzland.model;

import jakarta.persistence.*;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "KwalifikacjePracownika")
public class EmployeeQualificationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "KwalifikacjaID")
    private Integer employeeQualificationID;
    @ManyToOne
    @JoinColumn(name = "KategoriaID")
    private ServiceCategoryModel serviceCategoryModel;
    @ManyToOne
    @JoinColumn(name = "PracownikID")
    private EmployeeModel employeeModel;
}
