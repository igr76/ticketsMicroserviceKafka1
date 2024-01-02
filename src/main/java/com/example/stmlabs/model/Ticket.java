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
  @OneToOne
   Route route;
   LocalDate dateTime;
   int place;
   int cost;
   @ManyToOne(fetch = FetchType.EAGER)
   User user;
}

