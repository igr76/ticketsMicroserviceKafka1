package com.example.stmlabs.service;

import com.example.stmlabs.dto.TicketDto;
import com.example.stmlabs.model.Ticket;

import java.time.LocalDateTime;
import java.util.List;

public interface TicketService {
    List<Ticket> getAllTickets();
    List<Ticket> getAllTicketsChoose(LocalDateTime date,String departurePoints,String carrier);
    List<Ticket> getAllMyTickets();
    void buyTicket(TicketDto ticketDto);
    Ticket greatTicket(TicketDto ticketDto);
    Ticket deleteTicket(TicketDto ticketDto);

}
