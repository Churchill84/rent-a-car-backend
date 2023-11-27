package com.rentacar.request;

import com.rentacar.enumeration.CarStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
}
