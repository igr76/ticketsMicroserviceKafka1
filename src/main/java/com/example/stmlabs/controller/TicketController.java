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
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/ticket")
@Slf4j
@RestController
public class TicketController {
    private TicketService ticketService;
    @Operation(summary = "Получить пользователя")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                    array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
            @ApiResponse(responseCode = "204", description = "No Content", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema()))
    })
    @GetMapping(value = "/all")
    public ResponseEntity<List<Ticket>> getAllTickets() {
        log.info("controller Получить пользователя");
        return ResponseEntity.ok(ticketService.getAllTickets());
    }
    @Operation(summary = "Получить пользователя")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                    array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
            @ApiResponse(responseCode = "204", description = "No Content", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema()))
    })
    @GetMapping(value = "/{date}/{departurePoints}/{carrier}")
    public ResponseEntity<List<Ticket>> getAllTicketsChoose(@PathVariable(name = "date")
                                                                @NotBlank(message = "ad_pk не должен быть пустым") LocalDateTime date,
                                                            @PathVariable(name = "date")
                                                            @NotBlank(message = "ad_pk не должен быть пустым") String departurePoints,
                                                            @PathVariable(name = "date")
                                                                @NotBlank(message = "ad_pk не должен быть пустым") String carrier) {
        log.info("controller Получить пользователя");
        return ResponseEntity.ok(ticketService.getAllTicketsChoose(date,departurePoints,carrier));
    }
    @Operation(summary = "Получить пользователя")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                    array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
            @ApiResponse(responseCode = "204", description = "No Content", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema()))
    })
    @GetMapping(value = "/allMy")
    public ResponseEntity<List<Ticket>> getAllMyTickets() {
        log.info("controller Получить пользователя");
        return ResponseEntity.ok(ticketService.getAllMyTickets());
    }
    @Operation(summary = "Создать пользователя")
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
    public ResponseEntity<Ticket> greatTicket(
            @RequestBody
            @NotBlank(message = "пользователь не должен быть пустым") TicketDto ticketDto/*, Authentication authentication*/) {
        log.info("controller создать пользователя");
        return ResponseEntity.ok(ticketService.greatTicket(ticketDto));
    }
    @Operation(summary = "Удалить пользователя")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                    array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
            @ApiResponse(responseCode = "204", description = "No Content", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema()))
    })
    //@PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{login}")
    public void deleteTicket(  @RequestBody
                                   @NotBlank(message = "пользователь не должен быть пустым")TicketDto ticketDto/*, Authentication authentication*/) {
        log.info("controller Удалить пользователя");
        ticketService.deleteTicket(ticketDto);
    }
}
