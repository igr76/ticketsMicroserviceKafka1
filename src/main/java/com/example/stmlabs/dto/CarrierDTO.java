package com.example.stmlabs.dto;

import lombok.*;

/** DTO перевозчик  */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarrierDTO {
    @NonNull
    private String name;
    private String phone;
}
