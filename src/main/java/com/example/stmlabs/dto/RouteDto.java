package com.example.stmlabs.dto;

import com.example.stmlabs.model.Carrier;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** DTO маршрут  */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RouteDto {
    private String arrivalPoint;
    private String departurePoint;
    private long carrier;
    private int durationInMinutes;
}
