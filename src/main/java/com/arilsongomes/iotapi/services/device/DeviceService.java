package com.arilsongomes.iotapi.services.device;

import com.arilsongomes.iotapi.dto.device.DeviceRequestDto;
import com.arilsongomes.iotapi.dto.device.DeviceResponseDto;

import java.util.List;

public interface DeviceService {
    public void registerDevice(DeviceRequestDto deviceRequestDto, String email);
    public void updateDevice(String deviceId, DeviceRequestDto deviceRequestDto, String email);
    public List<DeviceResponseDto> getAllDeviceUser(String email);
    public void removeDevice(String deviceId, String email);
}
