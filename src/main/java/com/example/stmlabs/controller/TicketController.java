package com.example.stmlabs.controller;

import com.example.stmlabs.dto.NewTicketDto;
import com.example.stmlabs.dto.TicketDto;
import com.example.stmlabs.dto.UserDto;
import com.example.stmlabs.model.Ticket;
import com.example.stmlabs.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
/** Контроллер билетов  */
@RequestMapping("/ticket")
@Slf4j
@RestController
public class TicketController {
    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Operation(summary = "Получить билеты")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                    array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
            @ApiResponse(responseCode = "204", description = "No Content", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema()))
    })
    @GetMapping(value = "/all")
    public ResponseEntity<List<TicketDto>> getAllTickets(@RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
                                                         @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit) {
        log.debug("controller Получить все  билет");
        return ResponseEntity.ok(ticketService.getAllTickets(PageRequest.of(offset,limit)));
    }
    @Operation(summary = "Получить билет")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                    array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
            @ApiResponse(responseCode = "204", description = "No Content", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema()))
    })
    @GetMapping(value = "/allChoose")
    public ResponseEntity<List<TicketDto>> getAllTicketsChoose(@RequestParam(name = "date")
                                                                   @NotBlank(message = "дата не должна быть пустой")
                                                                   String date,
                                                               @RequestParam(name = "arrivalPoint",required = false) String arrivalPoint,
                                                               @RequestParam(name = "departurePoints",required = false) String departurePoints,
                                                               @RequestParam(name = "carrier",required = false) String carrier,
                                                               @RequestParam(name = "offset", defaultValue = "0") @Min(0) Integer offset,
                                                               @RequestParam(name = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit) {
        log.debug("controller Получить билет");
        return ResponseEntity.ok(ticketService.getAllTicketsChoose(date,arrivalPoint,departurePoints,carrier,limit,offset));
    }
    @Operation(summary = "Получить свои билеты")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                    array = @ArraySchema(schema = @Schema(implementation = TicketDto.class)))),
            @ApiResponse(responseCode = "204", description = "No Content", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema()))
    })
    @GetMapping(value = "/allMy")
    public ResponseEntity<List<TicketDto>> getAllMyTickets(HttpServletRequest request, Authentication authentication) {
        log.info("controller Получить свои билеты");
        String token = request.getHeader("Authorization");
        return ResponseEntity.ok(ticketService.getAllMyTickets(token, authentication.getName()));
    }
    @Operation(summary = "Создать билет")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                    array = @ArraySchema(schema = @Schema(implementation = TicketDto.class)))),
            @ApiResponse(responseCode = "204", description = "No Content", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema()))
    })
    @PostMapping(value = "buy")
    public ResponseEntity<List<TicketDto>> buyTicket(@RequestParam(name = "id",required = false)
                                                           @NotBlank(message = "id не должен быть пустым")long id,
                                                           @RequestParam(name = "arrivalPoint",required = false)
                                                           @NotBlank(message = "login не должен быть пустым") String login) {
        log.debug("controller создать билет");
        return ResponseEntity.ok(ticketService.buyTicket(id,login));
    }
    @Operation(summary = "Создать билет")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                    array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
            @ApiResponse(responseCode = "204", description = "No Content", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema()))
    })
    @PostMapping
    public ResponseEntity<TicketDto> greatTicket(
            @RequestBody
            @NotBlank(message = "пользователь не должен быть пустым") NewTicketDto newTicketDto/*, Authentication authentication*/) {
        log.debug("controller создать билет");
        return ResponseEntity.ok(ticketService.greatTicket(newTicketDto));
    }
    @Operation(summary = "Удалить билет")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                    array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
            @ApiResponse(responseCode = "204", description = "No Content", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema()))
    })
    @DeleteMapping("/{id}")
    public void deleteTicket(  @RequestBody
                                   @NotBlank(message = "пользователь не должен быть пустым")long id/*, Authentication authentication*/) {
        log.debug("controller Удалить билет");
        ticketService.deleteTicket(id);
    }
}
