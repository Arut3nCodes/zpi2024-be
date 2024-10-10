package com.zpi.fryzland.model;


import jakarta.persistence.*;
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
    @Column(name = "NazwaS")
    private String salonName;
    @Column(name="NrTelS")
    @Size(min=7, max=16, message="foobar")
    private String salonDialNumber;
    @Column(name = "MiastoS")
    private String salonCity;
    @Column(name = "UlicaS")
    private String salonStreet;
    @Column(name = "NrBudynkuS")
    private String salonBuldingNumber;
    @Column(name = "KodPocztowyS")
    private String salonPostalCode;
}
