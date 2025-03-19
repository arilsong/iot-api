package com.arilsongomes.iotapi.services.user;

import com.arilsongomes.iotapi.dto.UserDto;
import com.arilsongomes.iotapi.entity.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    User createUser(UserDto userDto);

}
