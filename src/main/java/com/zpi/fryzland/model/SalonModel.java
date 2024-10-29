package com.zpi.fryzland.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "Salony")
public class SalonModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SalonID")
    private Integer SalonID;
    @Column(name = "NazwaS", nullable = false, unique = true, length = 50)
    private String salonName;
    @Column(name="NrTelS", nullable = false, unique = true)
    @Pattern(regexp="^\\+[0-9]{1,3}\\s[0-9]{5,12}$")
    private String salonDialNumber;
    @Column(name = "MiastoS", nullable = false, length = 100)
    private String salonCity;
    @Column(name = "UlicaS", nullable = false, length = 150)
    private String salonStreet;
    @Column(name = "NrBudynkuS", nullable = false, length = 30)
    private String salonBuildingNumber;
    @Pattern(regexp = "^[1-9]{2}-[1-9]{3}$")
    @Column(name = "KodPocztowyS", nullable = false)
    private String salonPostalCode;
}
