package com.example.consumer.service;

import com.example.consumer.model.HistoryTicket;
import com.example.consumer.repository.TicketHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TicketHistoryService implements TicketService{
    private TicketHistoryRepository repository;
    @Override
    public void greatTicket(List<HistoryTicket> historyTicketList){
        for (HistoryTicket e :
                historyTicketList) {
            repository.save(e);
        }
    };
}
