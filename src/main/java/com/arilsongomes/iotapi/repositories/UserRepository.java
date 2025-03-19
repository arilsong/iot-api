package com.arilsongomes.iotapi.repositories;

import com.arilsongomes.iotapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
