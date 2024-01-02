package com.example.stmlabs.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Cущность маршрут  */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "routes")
public class Route {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String arrivalPoint;
  private String departurePoint;
  @OneToOne(fetch = FetchType.EAGER)
  private Carrier carrier;
  private int durationInMinutes;


}
