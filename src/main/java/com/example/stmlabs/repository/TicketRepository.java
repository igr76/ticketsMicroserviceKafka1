package com.example.stmlabs.repository;

import com.example.stmlabs.dto.TicketDto;
import com.example.stmlabs.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
/** Репозиторий билетов  */
@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {
    List<Ticket> findAllByUserNull();
    @Query(nativeQuery = true, value ="SELECT * FROM tickets JOIN routes USING(route_id) JOIN carriers USING(carrier_id)" +
            "WHERE date = :date AND arrivalPoint = :arrivalPoint AND departurePoints = :departurePoints AND " +
            "carrier = :carrier AND tickets.user_id IS NULL  LIMIT :limit OFFSET :offset*limit")
    List<Ticket> getAllTicketsChoose(LocalDate date, String arrivalPoint,
                                     String departurePoints, String carrier, int limit, int offset);
    @Query(nativeQuery = true, value ="SELECT * FROM tickets JOIN routes USING(route_id) JOIN carriers USING(carrier_id)" +
            "WHERE date = :date AND arrivalPoint = :arrivalPoint AND tickets.user_id IS NULL  LIMIT :limit OFFSET :offset*limit")
    List<Ticket> getAllTicketsChooseOfArrivalPoint(LocalDate date, String arrivalPoint, int limit, int offset);
    @Query(nativeQuery = true, value ="SELECT * FROM tickets JOIN routes USING(route_id) JOIN carriers USING(carrier_id)" +
            "WHERE date = :date  AND departurePoints = :departurePoints AND " +
            "AND tickets.user_id IS NULL  LIMIT :limit OFFSET :offset*limit")
    List<Ticket> getAllTicketsChooseOfDeparturePoints(LocalDate date, String departurePoints,  int limit, int offset);
    @Query(nativeQuery = true, value ="SELECT * FROM tickets JOIN routes USING(route_id) JOIN carriers USING(carrier_id)" +
            "WHERE date = :date AND arrivalPoint = :arrivalPoint AND departurePoints = :departurePoints AND " +
            "tickets.user_id IS NULL  LIMIT :limit OFFSET :offset*limit")
    List<Ticket> getAllTicketsChooseNoCarrier(LocalDate date, String arrivalPoint,
                                     String departurePoints, int limit, int offset);
    @Query(nativeQuery = true, value ="SELECT * FROM tickets WHERE tickets.user_id = :id  ")
    List<Ticket> findAllByUser(long id);

}
