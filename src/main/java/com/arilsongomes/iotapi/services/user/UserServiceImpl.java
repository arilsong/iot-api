package com.arilsongomes.iotapi.services.user;

import com.arilsongomes.iotapi.dto.UserDto;
import com.arilsongomes.iotapi.entity.User;
import com.arilsongomes.iotapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    public User createUser(UserDto dto) {
        User user = new User();
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        return userRepository.save(user);
    }
}
