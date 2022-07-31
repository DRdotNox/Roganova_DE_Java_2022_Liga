package com.liga.homework.mapper;

import com.liga.homework.model.User;
import com.liga.homework.model.UserSecurityDTO;
import com.liga.homework.repo.UserRepo;
import lombok.RequiredArgsConstructor;

public class DTOMapper {
    public static UserSecurityDTO convertToUserSecurityDTO(User user){
        return UserSecurityDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }

    public static User convertToUser(UserSecurityDTO userSecurityDTO) {
        return User.builder()
                .id(userSecurityDTO.getId())
                .email(userSecurityDTO.getEmail())
                .password(userSecurityDTO.getPassword())
                .role(userSecurityDTO.getRole())
                .build();
    }
}
