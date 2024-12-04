package com.zpi.fryzland.model.authmodel;
import com.zpi.fryzland.model.CustomerModel;
import com.zpi.fryzland.model.enums.RequestType;
import com.zpi.fryzland.model.enums.VisitStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name="PodaniaKlientów")
public class CustomerRequestModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="PodanieID", nullable = false)
    private String customerRequestId;
    @PrePersist
    private void addPrefix() {
        if (customerRequestId != null) {
            customerRequestId = "C-" + customerRequestId;
        }
    }

    @Enumerated(EnumType.STRING)
    @Column(name="TypPodania", nullable = false)
    private RequestType requestType;

    @FutureOrPresent
    @Column(name="DataWygaśnięcia", nullable = false)
    private LocalDate employeeRequestExpirationDate;

    @Column(name="CzasWygaśnięcia", nullable = false)
    private LocalTime customerRequestExpirationTime;

    @ManyToOne
    @JoinColumn(name="KlientID", nullable = false)
    private CustomerModel customerModel;
}
