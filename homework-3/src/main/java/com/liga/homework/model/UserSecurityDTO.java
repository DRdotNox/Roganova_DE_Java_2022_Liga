package com.liga.homework.model;

import com.liga.homework.enums.UserRole;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSecurityDTO {

    private Long id;

    private String email;

    private String password;

    private UserRole userRole;

    @Override
    public String toString() {
            return getEmail() + "\n" + getUserRole()  + "\n";
        }

    }

