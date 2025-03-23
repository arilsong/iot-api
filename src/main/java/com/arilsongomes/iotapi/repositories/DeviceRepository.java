package com.arilsongomes.iotapi.repositories;

import com.arilsongomes.iotapi.entity.Device;
import com.arilsongomes.iotapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DeviceRepository extends JpaRepository<Device, String> {
    boolean existsByMacAdress(String mac);
    List<Device> findByUserId(Long UserId);
    @Query("SELECT d FROM Device d WHERE d.user = :user")
    List<Device> findByUser(@Param("user") User user);

    @Override
    Optional<Device> findById(String deviceId);
}
