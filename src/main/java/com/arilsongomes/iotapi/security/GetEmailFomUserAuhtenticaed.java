package com.arilsongomes.iotapi.security;

import com.arilsongomes.iotapi.exceptions.UnauthorizedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class GetEmailFomUserAuhtenticaed {

    public String getAuthenticatedEmail(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new UnauthorizedException("User not logged in");
        }

        return authentication.getName();
    }
}
