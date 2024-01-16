package com.example.stmlabs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**  Dto пассажир  */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UserDto {

  private String login;
  private String passwordHash;
  private String name;
  private String surname;
  private String patronymicName;
  private Role role;




}
