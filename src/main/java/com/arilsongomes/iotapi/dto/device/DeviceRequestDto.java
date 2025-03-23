package com.arilsongomes.iotapi.dto.device;

import jakarta.validation.constraints.NotBlank;

public record DeviceRequestDto(
        @NotBlank String macAdress,
        @NotBlank String name,
        @NotBlank String type) {
}
