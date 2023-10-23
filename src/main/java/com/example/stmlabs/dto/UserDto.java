package com.example.stmlabs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Cущность пассажир  */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

  private String login;
  private String passwordHash;
  private String name;
  private String surname;
  private String patronymicName;




}
