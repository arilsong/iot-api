package com.arilsongomes.iotapi.controllers;

import com.arilsongomes.iotapi.dto.device.DeviceRequestDto;
import com.arilsongomes.iotapi.dto.device.DeviceResponseDto;
import com.arilsongomes.iotapi.services.device.DeviceService;
import com.arilsongomes.iotapi.security.GetEmailFomUserAuhtenticaed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/device")
@RequiredArgsConstructor
public class DeviceController {
    private final DeviceService deviceService;
    private final GetEmailFomUserAuhtenticaed getEmailFomUserAuhtenticate;

    @PostMapping
    public ResponseEntity<String> registerDevice(@RequestBody @Valid DeviceRequestDto deviceRequestDto) {

        String email = getEmailFomUserAuhtenticate.getAuthenticatedEmail();
        deviceService.registerDevice(deviceRequestDto, email);
        return new ResponseEntity<>("Device registed", HttpStatus.OK);
    }

    @GetMapping
    public List<DeviceResponseDto> getAllDeviceUser(){
        String email = getEmailFomUserAuhtenticate.getAuthenticatedEmail();
        return deviceService.getAllDeviceUser(email);
    }

    @DeleteMapping("/{deviceId}")
    public ResponseEntity<Void> removeDevice(@PathVariable String deviceId) {
        String email = getEmailFomUserAuhtenticate.getAuthenticatedEmail();

        deviceService.removeDevice(deviceId, email);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{deviceId}")
    public String updateDevice(@PathVariable String deviceId, @RequestBody @Valid DeviceRequestDto deviceRequest) {
        String email = getEmailFomUserAuhtenticate.getAuthenticatedEmail();
        deviceService.updateDevice(deviceId, deviceRequest, email);
        return "user updated";
    }
}
