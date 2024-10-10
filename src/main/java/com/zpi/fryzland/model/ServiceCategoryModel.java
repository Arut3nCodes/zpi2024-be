package com.zpi.fryzland.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "KategoriaUslugi")
public class ServiceCategoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "KategoriaID")
    private Integer serviceCategoryId;
    @Column(name = "NazwaKat")
    private String categoryName;
    @Column(name = "OpisKat")
    private String categoryDescription;
}
