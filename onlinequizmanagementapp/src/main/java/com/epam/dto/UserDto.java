package com.epam.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;


@Setter
@NoArgsConstructor
@Getter
@ToString
public class UserDto {
    private int userId;
    @NotEmpty(message = "UserName should not be empty")
    private String userName;
    @NotEmpty(message = "Type should not be empty")
    private String type;
    @NotEmpty(message = "Password should not be empty")
    private String password;


    public UserDto(String userName, String type, String password) {
        this.userName = userName;
        this.type = type;
        this.password = password;
    }

}
