package com.example.stmlabs.dto;

import com.example.stmlabs.model.Carrier;
import jakarta.persistence.OneToOne;
import lombok.*;

/** DTO маршрут  */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RouteDto {
    @NonNull
    private String arrivalPoint;
    @NonNull
    private String departurePoint;
    private long carrier;
    private int durationInMinutes;
}
