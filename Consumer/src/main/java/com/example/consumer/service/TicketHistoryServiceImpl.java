package com.example.consumer.service;

import com.example.consumer.model.HistoryTicket;
import com.example.consumer.repository.TicketHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Реализация сервиса истории билетов
 */
@Service
public class TicketHistoryServiceImpl implements TicketHistoryService{
    private TicketHistoryRepository repository;
    /**   Создать билет*/
    @Override
    public void greatTicket(List<HistoryTicket> historyTicketList){
        for (HistoryTicket e :
                historyTicketList) {
            repository.save(e);
        }
    };
}
