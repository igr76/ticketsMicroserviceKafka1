package com.example.stmlabs.repository;

import com.example.stmlabs.dto.TicketDto;
import com.example.stmlabs.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {
    List<Ticket> findAllByUserNull();
    @Query(nativeQuery = true, value ="SELECT * FROM tickets JOIN routes USING(route_id) JOIN carriers USING(carrier_id)" +
            "WHERE date = :date AND arrivalPoint = :arrivalPoint AND departurePoints = :departurePoints AND " +
            "carrier = :carrier AND tickets.ticket_id IS NULL  LIMIT :limit OFFSET :offset*limit")
    List<Ticket> getAllTicketsChoose(LocalDateTime date, String arrivalPoint,
                                     String departurePoints, String carrier,int limit,int offset);
}
