package com.example.stmlabs.service.impl;

import com.example.stmlabs.dto.NewTicketDto;
import com.example.stmlabs.dto.Role;
import com.example.stmlabs.dto.TicketDto;
import com.example.stmlabs.exception.ElemNotFound;
import com.example.stmlabs.mapper.TicketMapper;
import com.example.stmlabs.model.Ticket;
import com.example.stmlabs.model.User;
import com.example.stmlabs.repository.TicketRepository;
import com.example.stmlabs.repository.UserRepository;
import com.example.stmlabs.service.TicketService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
@Transactional
@Service
public class TicketServiceImpl implements TicketService {
    private TicketRepository ticketRepository;
    private TicketMapper ticketMapper;
    private UserRepository userRepository;
    @Value("${jwt.secret.access}") String jwtAccessSecret;

    public TicketServiceImpl(TicketRepository ticketRepository, TicketMapper ticketMapper) {
        this.ticketRepository = ticketRepository;
        this.ticketMapper = ticketMapper;
    }
    /**   Получить все билеты */
    @Override
    public List<TicketDto> getAllTickets(PageRequest pageRequest) {
        log.debug("Service Получить  все билеты");
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
    @Cacheable(value = "getAllMyTickets", key = "#login")
    public List<TicketDto> getAllMyTickets(String token, String login) {
        log.debug("Service Получить  все свои билеты");
        return ticketMapper.toListDto(ticketRepository.findAllByUser(getUserIdFromToken(token)));
    }
    /**   Купить выбранный  билет */
    @Override
    @CachePut(value = "getAllMyTickets", key = "#login")
    public List<TicketDto> buyTicket(long id,Authentication authentication) {
        Ticket ticket=ticketRepository.findById(id).orElseThrow(ElemNotFound::new);
        User user=userRepository.findByLogin(authentication.getName()).orElseThrow(ElemNotFound::new);
        ticket.setUser(user);
        ticketRepository.save(ticket);
        return ticketMapper.toListDto(ticketRepository.findAllByUser(user.getId()));
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

    public long getUserIdFromToken(String token) {
        Jws<Claims> jwsClaims = Jwts.parserBuilder()
                .setSigningKey(jwtAccessSecret)
                .build()
                .parseClaimsJws(token);
        return jwsClaims.getBody().get("id",Long.class);
    }

}
