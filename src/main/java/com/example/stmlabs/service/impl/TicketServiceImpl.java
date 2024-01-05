package com.example.stmlabs.service.impl;

import com.example.stmlabs.dto.NewTicketDto;
import com.example.stmlabs.dto.TicketDto;
import com.example.stmlabs.exception.ElemNotFound;
import com.example.stmlabs.mapper.TicketMapper;
import com.example.stmlabs.model.Ticket;
import com.example.stmlabs.model.User;
import com.example.stmlabs.repository.TicketRepository;
import com.example.stmlabs.repository.UserRepository;
import com.example.stmlabs.service.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
/**
 * Реализация сервиса билетов
 */
@Slf4j
@Service
public class TicketServiceImpl implements TicketService {
    private TicketRepository ticketRepository;
    private TicketMapper ticketMapper;
    private UserRepository userRepository;

    public TicketServiceImpl(TicketRepository ticketRepository, TicketMapper ticketMapper) {
        this.ticketRepository = ticketRepository;
        this.ticketMapper = ticketMapper;
    }
    /**   Получить все билеты */
    @Override
    public List<TicketDto> getAllTickets(PageRequest pageRequest) {
        log.info("Service Получить  все билеты");
        return ticketMapper.toListDto(ticketRepository.findAllByUserNull());
    }
    /**   Получить билеты  по выборке*/
    @Override
    public List<TicketDto> getAllTicketsChoose(String stringDate,String arrivalPoint, String departurePoints,
                                               String carrier,int limit,int offset) {
        if (arrivalPoint == null  && departurePoints == null){ throw  new ElemNotFound("Укажите станцию");}
        List<Ticket> ticketList= new ArrayList<>();
        LocalDate date = LocalDate.parse(stringDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        if (arrivalPoint == null  && carrier == null) {
            ticketList = ticketRepository.getAllTicketsChooseOfDeparturePoints(date,departurePoints,limit,offset);
            return ticketMapper.toListDto(ticketList);
        }else if (departurePoints == null  && carrier == null) {
            ticketList = ticketRepository.getAllTicketsChooseOfArrivalPoint(date,arrivalPoint,limit,offset);
            return ticketMapper.toListDto(ticketList);
        }else if ( carrier == null) {
            ticketList = ticketRepository.getAllTicketsChooseNoCarrier(date,arrivalPoint,departurePoints,limit,offset);
            return ticketMapper.toListDto(ticketList);
        }
        ticketList = ticketRepository.getAllTicketsChoose(date,arrivalPoint,departurePoints,carrier,limit,offset);
        return ticketMapper.toListDto(ticketList);
    }
    /**   Получить все свои билеты */
    @Override
    public List<TicketDto> getAllMyTickets( Authentication authentication) {
        log.info("Service Получить  все свои билеты");
        User user=userRepository.findByLogin(authentication.getName()).orElseThrow(ElemNotFound::new);
        return ticketMapper.toListDto(ticketRepository.findAllByUser(user.getId()));
    }
    /**   Купить выбранный  билет */
    @Override
    public void buyTicket(long id,String login) {
        Ticket ticket=ticketRepository.findById(id).orElseThrow(ElemNotFound::new);
        User user=userRepository.findByLogin(login).orElseThrow(ElemNotFound::new);
        ticket.setUser(user);
        ticketRepository.save(ticket);
    }
    /**   Создать билет*/
    @Override
    public TicketDto greatTicket(NewTicketDto newTicketDto) {
        return ticketMapper.toDTO(ticketRepository.save(ticketMapper.toEntityISNew(newTicketDto)));
    }
    /**   Удалить билет*/
    @Override
    public void deleteTicket(long id) {
        ticketRepository.delete(ticketRepository.findById(id).orElseThrow(ElemNotFound::new));
    }
}
