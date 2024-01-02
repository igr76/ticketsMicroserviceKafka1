package com.example.consumer.repository;

import com.example.consumer.model.HistoryTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketHistoryRepository extends JpaRepository<HistoryTicket,Long> {
}
