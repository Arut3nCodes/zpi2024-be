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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SalonID")
    private Integer SalonID;
    @Column(name = "NazwaS", nullable = false, unique = true)
    private String salonName;
    @Column(name="NrTelS", nullable = false, unique = true)
    @Pattern(regexp="^\\+[0-9]{1,3}\\s[0-9]{5,12}$")
    private String salonDialNumber;
    @Column(name = "MiastoS", nullable = false)
    private String salonCity;
    @Column(name = "UlicaS", nullable = false)
    private String salonStreet;
    @Column(name = "NrBudynkuS", nullable = false)
    private String salonBuldingNumber;
    @Column(name = "KodPocztowyS", nullable = false)
    private String salonPostalCode;
}
