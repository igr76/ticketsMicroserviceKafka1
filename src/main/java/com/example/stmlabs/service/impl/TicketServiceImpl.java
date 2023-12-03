package com.example.stmlabs.service.impl;

import com.example.stmlabs.dto.TicketDto;
import com.example.stmlabs.mapper.TicketMapper;
import com.example.stmlabs.model.Ticket;
import com.example.stmlabs.repository.TicketRepository;
import com.example.stmlabs.service.TicketService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class TicketServiceImpl implements TicketService {
    private TicketRepository ticketRepository;
    private TicketMapper ticketMapper;

    public TicketServiceImpl(TicketRepository ticketRepository, TicketMapper ticketMapper) {
        this.ticketRepository = ticketRepository;
        this.ticketMapper = ticketMapper;
    }

    @Override
    public List<Ticket> getAllTickets() {
        return null;
    }

    @Override
    public List<Ticket> getAllTicketsChoose(LocalDateTime date, String departurePoints, String carrier) {
        return null;
    }

    @Override
    public List<Ticket> getAllMyTickets() {
        return null;
    }

    @Override
    public void buyTicket(TicketDto ticketDto) {

    }

    @Override
    public Ticket greatTicket(TicketDto ticketDto) {
        return  ticketRepository.save(ticketMapper.toEntity(ticketDto));
    }

    @Override
    public Ticket deleteTicket(TicketDto ticketDto) {
        return null;
    }
}
