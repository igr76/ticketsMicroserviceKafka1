package com.example.consumer.service;

import com.example.consumer.model.HistoryTicket;

import java.util.List;
/**
 * Cервис истории билетов
 */
public interface TicketHistoryService {
    /**   Создать билет*/
    void greatTicket(List<HistoryTicket> historyTicketList);
}
