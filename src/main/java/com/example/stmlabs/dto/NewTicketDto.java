package com.example.stmlabs.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
/** DTO билет  */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewTicketDto {
    long route;
    LocalDateTime dateTime;
    int place;
    int cost;
}
