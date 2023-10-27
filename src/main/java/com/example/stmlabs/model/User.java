package com.example.stmlabs.model;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

/** Cущность пассажир  */
@Getter
@Setter
@ToString
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
//{"login": "user1",
//        "passwordHash": "user1",
//        "name": "user1",
//        "surname": "user1",
//        "patronymicName": "user1"}
