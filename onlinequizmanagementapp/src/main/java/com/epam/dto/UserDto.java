package com.epam.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.validation.constraints.NotEmpty;


@Setter
@NoArgsConstructor
@Getter
public class UserDto {
    private int userId;
    @NotEmpty(message = "UserName should not be empty")
    private String username;
    @NotEmpty(message = "Role should not be empty")
    private String role;
    @NotEmpty(message = "Password should not be empty")
    private String password;


    public UserDto(String userName, String type, String password) {
        this.username = userName;
        this.role = type;
        this.password = password;
    }

}
