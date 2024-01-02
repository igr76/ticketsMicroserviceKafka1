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
@Table(name = "histurytickets")
public class HistoryTicket {
  @Id
  long id;
    long route;
    String dateTime;
    int place;
    int cost;
    int user;
}
//{"id": 1,
//        "dateTime": "20-02-2023 ",
//        "place": 1,
//        "cost": 1,
//        "user": 1}
