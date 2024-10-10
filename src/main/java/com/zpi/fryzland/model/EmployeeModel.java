package com.zpi.fryzland.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "Pracownicy")
public class EmployeeModel {
    //todo: Dodać odpowiednie liczności, unikalność oraz opcjonalność kolumn
    @Id
    @Column(name = "PracownikID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer employeeID;
    @Column(name = "ImieP", length = 50)
    private String employeeName;
    @Column(name = "NazwiskoP", length = 50)
    private String employeeSurname;
    @Column(name = "NrTelP")
    @Size(min = 7, max= 16, message = "Dial Number length must be in betwen 7 and 16")
    private String employeeDialNumber;
    @Column(name = "HasloP")
    private String encryptedEmployeePassword;
    @Column(name = "EmailP")
    private String employeeEmail;
    @Column(name = "DataUrP")
    private Date employeeBirthdayDate;
    @Column(name = "DataZatrP")
    private Date employeeEmploymentDate;
    @Column(name = "PensjaP")
    private Float employeeMonthlyPay;
    @Column(name = "MiastoP")
    private String employeeCity;
    @Column(name = "UlicaP")
    private String employeeStreet;
    @Column(name = "NrBudynkuP")
    private String employeeBuldingNumber;
    @Column(name = "NrMieszkaniaP")
    private String employeeApartmentNumber;
    @Column(name = "KodPocztowyP")
    private String employeePostalCode;
}
