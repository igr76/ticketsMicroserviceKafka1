package com.example.stmlabs.mapper;

import com.example.stmlabs.dto.TicketDto;
import com.example.stmlabs.dto.UserDto;
import com.example.stmlabs.model.Ticket;
import com.example.stmlabs.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(componentModel = "spring")
public interface TicketMapper {
    @Mapping(target = "route.id", source = "route")
    @Mapping(target = "id", ignore = true)
    Ticket toEntity(TicketDto ticketDto);

    @Mapping(target = "route", source = "route.id")
    TicketDto toDTO(Ticket ticket);
}
