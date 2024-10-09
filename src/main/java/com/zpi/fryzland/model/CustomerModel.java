package com.zpi.fryzland.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name="Klienci")
@Table(name="Klienci")
public class CustomerModel {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="KlientID")
    private Integer customerID;
    @Column(name="ImieK", length = 50)
    private String customerName;
    @Column(name="NazwiskoK", length = 50)
    private String customerSurname;
    @Column(name="NrTelK")
    @Size(min=7, max=16, message="Surname must be in between 7 and 16")
    private String customerDialNumber;
    //todo Uzupelnic definicje kolumn :)
    @Column(name="HasloK")
    private String encryptedCustomerPassword;

//    @Column(name="PrefU", columnDefinition="Preferowane uslugi klienta")
//    private String preferredServices;
}
