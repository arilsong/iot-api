package com.arilsongomes.iotapi.repositories;

import com.arilsongomes.iotapi.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long> {

}
