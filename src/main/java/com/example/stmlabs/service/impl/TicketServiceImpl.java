package com.example.stmlabs.service.impl;

import com.example.stmlabs.dto.TicketDto;
import com.example.stmlabs.exception.ElemNotFound;
import com.example.stmlabs.mapper.TicketMapper;
import com.example.stmlabs.model.Ticket;
import com.example.stmlabs.model.User;
import com.example.stmlabs.repository.TicketRepository;
import com.example.stmlabs.repository.UserRepository;
import com.example.stmlabs.service.TicketService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class TicketServiceImpl implements TicketService {
    private TicketRepository ticketRepository;
    private TicketMapper ticketMapper;
    private UserRepository userRepository;

    public TicketServiceImpl(TicketRepository ticketRepository, TicketMapper ticketMapper) {
        this.ticketRepository = ticketRepository;
        this.ticketMapper = ticketMapper;
    }

    @Override
    public List<TicketDto> getAllTickets(PageRequest pageRequest) {
        return ticketMapper.toListDto(ticketRepository.findAllByUserNull());
    }

    @Override
    public List<TicketDto> getAllTicketsChoose(LocalDateTime date,String arrivalPoint, String departurePoints,
                                               String carrier,int limit,int offset) {
       List<Ticket> ticketList= ticketRepository.getAllTicketsChoose(date,arrivalPoint,departurePoints,carrier,limit,offset);
        return ticketMapper.toListDto(ticketList);
    }

    @Override
    public List<TicketDto> getAllMyTickets() {
        return null;
    }

    @Override
    public void buyTicket(long id,String login) {
        Ticket ticket=ticketRepository.findById(id).orElseThrow(ElemNotFound::new);
        User user=userRepository.findByLogin(login).orElseThrow(ElemNotFound::new);
        ticket.setUser(user);
        ticketRepository.save(ticket);
    }

    @Override
    public TicketDto greatTicket(TicketDto ticketDto) {
         ticketRepository.save(ticketMapper.toEntity(ticketDto));
        return ticketDto;
    }

    @Override
    public void deleteTicket(long id) {
        ticketRepository.delete(ticketRepository.findById(id).orElseThrow(ElemNotFound::new));
    }
}
