package com.example.stmlabs.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
/** Cущность билет  */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "tickets")
public class Ticket {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
   long id;
    /**  маршрут  */
  @OneToOne
   Route route;
    /** Дата билета  */
   LocalDate dateTime;
    /** Номер места  */
   int place;
    /** Стоимость билета  */
   int cost;
    /** Владелец  билета  */
   @ManyToOne(fetch = FetchType.EAGER)
   User user;
}

