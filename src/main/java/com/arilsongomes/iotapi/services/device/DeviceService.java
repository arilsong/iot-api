package com.arilsongomes.iotapi.services.device;

import com.arilsongomes.iotapi.dto.DeviceDto;

public interface DeviceService {
    public void registerDevice(DeviceDto deviceDto);
    public void getAllDevice(Long userId);
    public void removeDevice(Long id, Long userId);
}
