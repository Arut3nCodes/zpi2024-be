package com.zpi.fryzland.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name="Klienci")
public class CustomerModel {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="KlientID")
    private Integer customerID;
    @Column(name="ImieK", length = 50, nullable = false)
    private String customerName;
    @Column(name="NazwiskoK", length = 50)
    private String customerSurname;
    @Column(name="NrTelK", unique = true, nullable = false)
    @Pattern(regexp="^\\+[0-9]{1,3}\\s[0-9]{5,12}$")
    private String customerDialNumber;
    //todo Uzupelnic
    @Column(name="HasloK", nullable = false, length = 512)
    private String encryptedCustomerPassword;
    @Column(name="EmailK", unique = true, nullable = false)
    @Pattern(regexp="^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    private String customerEmail;
    @ManyToOne
    @JoinColumn(name="PrefKatU")
    private ServiceCategoryModel serviceCategoryModel;
}
