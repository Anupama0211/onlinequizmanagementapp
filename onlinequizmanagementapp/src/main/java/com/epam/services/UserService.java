package com.epam.services;


import com.epam.dao.UserRepository;
import com.epam.dto.UserDto;
import com.epam.entities.User;
import com.epam.exceptions.UserNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;


import java.util.Optional;


@Component
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    public boolean registerUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        boolean insertStatus = true;
        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            insertStatus = false;
        }
        return insertStatus;
    }

    public boolean validateCredentials(UserDto userDto)  {
        boolean check = false;

        Optional<User> userOptional = userRepository.findDistinctByUserName(userDto.getUserName());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getPassword().equals(userDto.getPassword()) &&
                    user.getType().equalsIgnoreCase(userDto.getType())) {
                check = true;
            }
        } else {
            throw new UserNotFoundException("User doesnt exist!! Register!");
        }

        return check;
    }
}


