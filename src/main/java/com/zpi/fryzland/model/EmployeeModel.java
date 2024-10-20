package com.zpi.fryzland.model;

import com.zpi.fryzland.validators.BirthdayDate;
import com.zpi.fryzland.validators.EmploymentDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "Pracownicy")
@EmploymentDate(employmentDateField = "employeeEmploymentDate", birthdayDateField = "employeeBirthdayDate")
public class EmployeeModel {
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
    @Column(name = "HasloP", nullable = false, length = 512)
    private String encryptedEmployeePassword;
    @Column(name = "EmailP", nullable = false, unique = true)
    @Pattern(regexp="^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    private String employeeEmail;
    @Column(name = "DataUrP", nullable = false)
    @BirthdayDate
    private LocalDate employeeBirthdayDate;
    @Column(name = "DataZatrP", nullable = false)
    private LocalDate employeeEmploymentDate;
    @Range(min = 1)
    @Column(name = "PensjaP", nullable = false)
    private Float employeeMonthlyPay;
    @Column(name = "MiastoP", nullable = false, length = 100)
    private String employeeCity;
    @Column(name = "UlicaP", nullable = false, length = 150)
    private String employeeStreet;
    @Column(name = "NrBudynkuP", nullable = false, length = 30)
    private String employeeBuildingNumber;
    @Column(name = "NrMieszkaniaP", nullable = false, length = 30)
    private String employeeApartmentNumber;
    @Pattern(regexp = "^[1-9]{2}-[1-9]{3}$")
    @Column(name = "KodPocztowyP", nullable = false)
    private String employeePostalCode;
}
