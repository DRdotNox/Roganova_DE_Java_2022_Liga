package com.liga.homework.model;

import com.liga.homework.enums.Role;
import lombok.*;
import org.springframework.stereotype.Indexed;

import javax.persistence.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSecurityDTO {

    private Long id;

    private String email;

    private String password;

    private Role role;

    @Override
    public String toString() {
            return getEmail() + "\n" + getRole()  + "\n";
        }

    }

