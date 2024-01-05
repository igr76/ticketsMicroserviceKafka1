package com.example.stmlabs.controller;

import com.example.stmlabs.dto.UserDto;
import com.example.stmlabs.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
/** Контроллер пассажиров  */
@RequestMapping("/users")
@Slf4j
@RestController
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }


  @Operation(summary = "Получить пассажира")
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                  array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
          @ApiResponse(responseCode = "204", description = "No Content", content = @Content(schema = @Schema())),
          @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema())),
          @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema())),
          @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema()))
  })
  //@PreAuthorize("hasAuthority('ADMIN')"+"|| 'user.login'")
  @GetMapping(value = "/{login}")
  public ResponseEntity<UserDto> getUser(@PathVariable(name = "login")
                                           @NotBlank(message = "ad_pk не должен быть пустым") String login, Authentication authentication) {
    log.info("controller Получить пассажира");
    return ResponseEntity.ok(userService.getUser(login,authentication));
  }
  @Operation(summary = "Создать пассажира")
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                  array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
          @ApiResponse(responseCode = "204", description = "No Content", content = @Content(schema = @Schema())),
          @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema())),
          @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema())),
          @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema()))
  })
  @PostMapping
  public ResponseEntity<UserDto> greaetUser(
          @RequestBody
          @NotBlank(message = "пассажир не должен быть пустым") UserDto userDto, Authentication authentication) {
    log.info("controller создать пассажира");
    return ResponseEntity.ok(userService.greateUser(userDto,authentication));
  }
  @Operation(summary = "Обновить пассажира")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "OK", content = @Content(
              array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
      @ApiResponse(responseCode = "204", description = "No Content", content = @Content(schema = @Schema())),
      @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema())),
      @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema())),
      @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema()))
  })
  //@PreAuthorize("userDto.login == authentication.principal.username")
  @PatchMapping()
  public ResponseEntity<UserDto> updateUser(
          @RequestBody
      @NotBlank(message = "пассажир не должен быть пустым") UserDto userDto, Authentication authentication) {
    log.info("controller Обновить пассажира");
    return ResponseEntity.ok(userService.updateUser(userDto,authentication));
  }
    @Operation(summary = "Удалить пассажира")
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
    public void deleteUser(@PathVariable(name = "login")
                               @NotBlank(message = "логин не должен быть пустым") String login, Authentication authentication) {
        log.info("controller Удалить пассажира");
         userService.deleteUser(login,authentication);
    }




}