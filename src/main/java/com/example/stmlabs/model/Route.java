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
  /** Станция отбытия */
  private String arrivalPoint;
  /** Станция Прибытия */
  private String departurePoint;
  /** Наименование перевозчика */
  @OneToOne(fetch = FetchType.EAGER)
  private Carrier carrier;
  /** Время в пути */
  private int durationInMinutes;


}
