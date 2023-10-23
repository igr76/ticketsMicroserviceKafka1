package com.example.stmlabs.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/** Cущность пассажир  */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String login;
  private String passwordHash;
  private String name;
  private String surname;
  private String patronymicName;



}
