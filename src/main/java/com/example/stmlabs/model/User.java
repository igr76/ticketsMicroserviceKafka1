package com.example.stmlabs.model;


import com.example.stmlabs.dto.Role;
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
  /** Логин пассажира  */
  private String login;
  /** Пароль пассажира  */
  private String passwordHash;
  /** Имя пассажира  */
  private String name;
  /** Фамилия пассажира  */
  private String surname;
  /** Отчество пассажира  */
  private String patronymicName;
  /** Роль пассажира  */
  private Role role;
}
//{"login": "user1",
//        "passwordHash": "user1",
//        "name": "user1",
//        "surname": "user1",
//        "patronymicName": "user1"}
