package com.example.stmlabs.mapper;

import com.example.stmlabs.dto.TicketDto;
import com.example.stmlabs.dto.UserDto;
import com.example.stmlabs.model.Ticket;
import com.example.stmlabs.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    @Mapping(target = "route.id", source = "route")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user.id", source = "user")
    @Mapping(target = "dateTime", source = "dateTime", dateFormat = "dd-MM-yyyy HH:mm:ss")
    Ticket toEntity(TicketDto ticketDto);

    @Mapping(target = "route", source = "route.id")
    @Mapping(target = "user", source = "user.id")
    @Mapping(target = "dateTime", source = "dateTime", dateFormat = "dd-MM-yyyy HH:mm:ss")
    TicketDto toDTO(Ticket ticket);
    List<TicketDto> toListDto(List<Ticket> ticketList);
}
