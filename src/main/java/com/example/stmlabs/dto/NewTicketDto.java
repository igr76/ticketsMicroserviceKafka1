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
    @NonNull
    long route;
    @NonNull
    String dateTime;
    @NonNull
    int place;
    int cost;
    int user;
}
