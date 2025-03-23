package com.arilsongomes.iotapi.exceptions;

public class DeviceAlreadyRegistedException extends RuntimeException{
    public DeviceAlreadyRegistedException(String message) {
        super(message);
    }
}
