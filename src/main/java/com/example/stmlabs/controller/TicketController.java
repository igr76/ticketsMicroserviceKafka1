package com.example.stmlabs.controller;

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
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/ticket")
@Slf4j
@RestController
public class TicketController {
    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
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
    @GetMapping(value = "/all/{offset}/{limit}")
    public ResponseEntity<List<TicketDto>> getAllTickets(@RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
                                                         @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit) {
        log.info("controller Получить билет");
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
    @GetMapping(value = "/{date}/{arrivalPoint}/{departurePoints}/{carrier}")
    public ResponseEntity<List<TicketDto>> getAllTicketsChoose(@PathVariable(name = "date")
                                                                   @NotBlank(message = "дата не должна быть пустой")
                                                                   LocalDateTime date,
                                                               @PathVariable(name = "arrivalPoint") String arrivalPoint,
                                                               @PathVariable(name = "departurePoints") String departurePoints,
                                                               @PathVariable(name = "carrier") String carrier,
                                                               @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
                                                               @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit) {
        log.info("controller Получить билет");
        return ResponseEntity.ok(ticketService.getAllTicketsChoose(date,arrivalPoint,departurePoints,carrier,limit,offset));
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
    @GetMapping(value = "/allMy")
    public ResponseEntity<List<TicketDto>> getAllMyTickets() {
        log.info("controller Получить билет");
        return ResponseEntity.ok(ticketService.getAllMyTickets());
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
    // @PreAuthorize("hasAuthority('ADMIN')"+"|| 'user.login'")
    @PostMapping
    public ResponseEntity<TicketDto> greatTicket(
            @RequestBody
            @NotBlank(message = "пользователь не должен быть пустым") TicketDto ticketDto/*, Authentication authentication*/) {
        log.info("controller создать билет");
        return ResponseEntity.ok(ticketService.greatTicket(ticketDto));
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
    //@PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteTicket(  @RequestBody
                                   @NotBlank(message = "пользователь не должен быть пустым")long id/*, Authentication authentication*/) {
        log.info("controller Удалить билет");
        ticketService.deleteTicket(id);
    }
}
