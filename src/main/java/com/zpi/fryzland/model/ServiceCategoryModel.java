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
    @Column(name = "NazwaKat", nullable = false, unique = true, length = 50)
    private String categoryName;
    @Column(name = "OpisKat", length = 200)
    private String categoryDescription;
}
