package com.example.stmlabs;

import com.example.stmlabs.dto.NewTicketDto;
import com.example.stmlabs.dto.TicketDto;
import com.example.stmlabs.mapper.TicketMapper;
import com.example.stmlabs.model.Ticket;
import com.example.stmlabs.repository.TicketRepository;
import com.example.stmlabs.service.impl.TicketServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {
    @Mock
    private TicketRepository ticketRepository;
    @Mock
    private TicketMapper ticketMapper;
    @InjectMocks
    private TicketServiceImpl ticketService =new TicketServiceImpl(ticketRepository,ticketMapper);

    @Test
    void getAllTickets() {
        int offset=1;int limit=1;
        List<TicketDto> ticketDtoList = new ArrayList<>();
        ticketDtoList.add(getTicketDto());
        List<Ticket> ticketList=new ArrayList<>();
        ticketList.add(getTicket());
        when(ticketRepository.findAllByUserNull()).thenReturn(ticketList);
        when(ticketMapper.toListDto(any())).thenReturn(ticketDtoList);
        assertThat(ticketService.getAllTickets(PageRequest.of(offset, limit))).isEqualTo(ticketDtoList);
        verify(ticketRepository, times(1)).findAllByUserNull();

    }
    @Test
    void getAllMyTickets() {

    }

    @Test
    void greatTicket() {
        TicketDto ticketDto =getTicketDto();Ticket ticket=getTicket();
        NewTicketDto newTicketDto= new NewTicketDto(1,
                "20-02-2023 ",1,1,0);
        when(ticketMapper.toEntityISNew(any())).thenReturn(ticket);
        when(ticketMapper.toDTO(any())).thenReturn(ticketDto);
        assertThat(ticketService.greatTicket(newTicketDto)).isEqualTo(ticketDto);
        verify(ticketRepository, times(1)).save(any());

    }
    @Test
    void deleteTicket() {
        Ticket ticket=getTicket();
        when(ticketRepository.findById(anyLong())).thenReturn(Optional.ofNullable(ticket));
        ticketService.deleteTicket(1);
        verify(ticketRepository, times(1)).findById(any());
    }

    TicketDto getTicketDto() {
        TicketDto ticketDto= new TicketDto(1,1,
                "20-02-2023 ",1,1,0);
        return ticketDto;
    }

    Ticket getTicket() {
        Ticket ticket = new Ticket(1,null,LocalDate.of(2023,01,12)
                ,1,1,null);
        return ticket;
    }
}
