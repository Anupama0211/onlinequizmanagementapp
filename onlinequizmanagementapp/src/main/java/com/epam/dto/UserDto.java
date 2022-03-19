package com.epam.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@NoArgsConstructor
@Getter
@ToString
public class UserDto {
    private int userId;

    public UserDto(String userName, String type, String password) {
        this.userName = userName;
        this.type = type;
        this.password = password;
    }

    private String userName;
    private String type;
    private String password;


}
