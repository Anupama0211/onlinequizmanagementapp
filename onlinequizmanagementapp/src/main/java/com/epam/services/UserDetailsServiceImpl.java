package com.epam.services;


import com.epam.dao.UserRepository;
import com.epam.dto.UserDto;
import com.epam.entities.User;
import com.epam.exceptions.UserNotFoundException;
import com.epam.model.UserDetailsModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;


    public UserDto registerUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return modelMapper.map(userRepository.save(user), UserDto.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findDistinctByUsername(username);
        return new UserDetailsModel(userOptional
                .orElseThrow(() -> new UserNotFoundException("User Not Found!!")));
    }

}



