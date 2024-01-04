package com.example.stmlabs.service;

import com.example.stmlabs.dto.NewTicketDto;
import com.example.stmlabs.dto.TicketDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;

import java.time.LocalDateTime;
import java.util.List;
/**
 * Сервис билетов
 */
public interface TicketService {
    /**   Получить все билеты */
    List<TicketDto> getAllTickets(PageRequest pageRequest);
    /**   Получить билеты  по выборке*/
    List<TicketDto> getAllTicketsChoose(String date,String arrivalPoint,
                                        String departurePoints,String carrier,int limit,int offset);
    /**   Получить все свои билеты */
    List<TicketDto> getAllMyTickets( Authentication authentication);
    /**   Купить выбранный  билет */
    void buyTicket(long id,String login);
    /**   Создать билет*/
    TicketDto greatTicket(NewTicketDto newTicketDto);
    /**   Удалить билет*/
    void deleteTicket(long id);

}
