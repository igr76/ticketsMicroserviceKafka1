package com.example.stmlabs.dto;

import com.example.stmlabs.model.Route;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
/** DTO билет  */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TicketDto {
     long id;
     long route;
     String dateTime;
     int place;
     int cost;
     int user;
}
