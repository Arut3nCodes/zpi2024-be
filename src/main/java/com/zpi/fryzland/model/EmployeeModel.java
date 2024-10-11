package com.zpi.fryzland.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "Pracownicy")
@Table(name = "Pracownicy")
public class EmployeeModel {
    //todo: Dodać odpowiednie liczności, unikalność oraz opcjonalność kolumn
    @Id
    @Column(name = "PracownikID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer employeeID;
    @Column(name = "ImieP", length = 50, nullable = false)
    private String employeeName;
    @Column(name = "NazwiskoP", length = 50, nullable = false)
    private String employeeSurname;
    @Column(name = "NrTelP", nullable = false, unique = true)
    @Pattern(regexp="^\\+[0-9]{1,3}\\s[0-9]{5,12}$")
    private String employeeDialNumber;
    @Column(name = "HasloP", nullable = false)
    private String encryptedEmployeePassword;
    @Column(name = "EmailP", nullable = false, unique = true)
    @Pattern(regexp="^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    private String employeeEmail;
    @Column(name = "DataUrP", nullable = false)

    private Date employeeBirthdayDate;
    @Column(name = "DataZatrP", nullable = false)
    private Date employeeEmploymentDate;
    @Column(name = "PensjaP", nullable = false)
    private Float employeeMonthlyPay;
    @Column(name = "MiastoP", nullable = false)
    private String employeeCity;
    @Column(name = "UlicaP", nullable = false)
    private String employeeStreet;
    @Column(name = "NrBudynkuP", nullable = false)
    private String employeeBuldingNumber;
    @Column(name = "NrMieszkaniaP", nullable = false)
    private String employeeApartmentNumber;
    @Column(name = "KodPocztowyP", nullable = false)
    private String employeePostalCode;
}
