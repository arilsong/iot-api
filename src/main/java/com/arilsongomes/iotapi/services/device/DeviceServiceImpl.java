package com.arilsongomes.iotapi.services.device;

import com.arilsongomes.iotapi.dto.device.DeviceRequestDto;
import com.arilsongomes.iotapi.dto.device.DeviceResponseDto;
import com.arilsongomes.iotapi.entity.Device;
import com.arilsongomes.iotapi.entity.User;
import com.arilsongomes.iotapi.exceptions.DeviceAlreadyRegistedException;
import com.arilsongomes.iotapi.exceptions.UnauthorizedException;
import com.arilsongomes.iotapi.repositories.DeviceRepository;
import com.arilsongomes.iotapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService{
    private final DeviceRepository deviceRepository;
    private final UserRepository userRepository;

    @Override
    public void registerDevice(DeviceRequestDto deviceRequestDto, String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new UnauthorizedException("User not found");
        }

        if (deviceRepository.existsByMacAdress(deviceRequestDto.macAdress())) {
            throw new DeviceAlreadyRegistedException("Device Already Registed");
        }

        Device device = new Device();
        device.setName(deviceRequestDto.name());
        device.setMacAdress(deviceRequestDto.macAdress());
        device.setType(deviceRequestDto.type());
        device.setUser(user.get());

        deviceRepository.save(device);
    }

    @Override
    @Transactional
    public void updateDevice(String deviceId , DeviceRequestDto deviceRequest, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UnauthorizedException("User not found"));

        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new RuntimeException("Device not found"));

        if(!device.getUser().equals(user)){
            throw new UnauthorizedException("Device does not belong to the user");
        }

        device.setMacAdress(deviceRequest.macAdress());
        device.setType(deviceRequest.type());
        device.setName(deviceRequest.name());

        deviceRepository.save(device);
    }

    @Override
    public List<DeviceResponseDto> getAllDeviceUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UnauthorizedException("User not found"));

        return deviceRepository.findByUser(user).stream()
                .map(device -> new DeviceResponseDto(
                        device.getName(),
                        device.getMacAdress(),
                        device.getType()
                ))
                .toList();
    }

    @Override
    @Transactional
    public void removeDevice(String deviceId, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UnauthorizedException("User not found"));

        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new RuntimeException("Device not found"));

        if(!device.getUser().equals(user)){
            throw new UnauthorizedException("Device does not belong to the user");
        }

        deviceRepository.delete(device);
    }
}
