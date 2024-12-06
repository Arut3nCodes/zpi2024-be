package com.zpi.fryzland.model.authmodel;
import com.zpi.fryzland.model.EmployeeModel;
import com.zpi.fryzland.model.enums.RequestType;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name="PodaniaPracowników")
public class EmployeeRequestModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="PodanieID", nullable = false)
    private String employeeRequestId;

    @Enumerated(EnumType.STRING)
    @Column(name="TypPodania", nullable = false)
    private RequestType requestType;

    @FutureOrPresent
    @Column(name="DataWygaśnięcia", nullable = false)
    private LocalDate employeeRequestExpirationDate;

    @Column(name="CzasWygaśnięcia", nullable = false)
    private LocalTime employeeRequestExpirationTime;

    @ManyToOne
    @JoinColumn(name="PracownikID", nullable = false)
    private EmployeeModel employeeModel;
}
