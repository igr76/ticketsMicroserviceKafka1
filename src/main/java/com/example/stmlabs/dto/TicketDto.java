package com.example.stmlabs.dto;

import com.example.stmlabs.model.Route;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
/** DTO билет  */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TicketDto {
    private long route;
    private LocalDateTime dateTime;
    private int place;
    private int cost;
}
