package com.example.consumer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

/** Cущность билет  */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "historytickets")
public class HistoryTicket {
  @Id
   long id;
  /**  маршрут  */
    long route;
  /** Дата билета  */
    String dateTime;
    /** Номер места  */
    int place;
     /** Стоимость билета  */
    int cost;
   /** Владелец  билета  */
    int user;
}

