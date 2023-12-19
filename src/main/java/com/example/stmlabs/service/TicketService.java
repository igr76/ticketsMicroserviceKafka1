package com.example.stmlabs.service;

import com.example.stmlabs.dto.TicketDto;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.List;

public interface TicketService {
    List<TicketDto> getAllTickets(PageRequest pageRequest);
    List<TicketDto> getAllTicketsChoose(LocalDateTime date,String arrivalPoint,
                                        String departurePoints,String carrier,int limit,int offset);
    List<TicketDto> getAllMyTickets();
    void buyTicket(long id,String login);
    TicketDto greatTicket(TicketDto ticketDto);
    void deleteTicket(long id);

}
