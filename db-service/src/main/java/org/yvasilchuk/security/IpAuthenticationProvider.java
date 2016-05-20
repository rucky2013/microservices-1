package org.yvasilchuk.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.yvasilchuk.domain.FatGrantedAuthority;
import org.yvasilchuk.domain.enums.Role;
import org.yvasilchuk.service.SecurityService;

import java.util.Collections;

public class IpAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    SecurityService securityService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        WebAuthenticationDetails wad;
        String userIPAddress;
        boolean isAuthenticatedByIP;

        wad = (WebAuthenticationDetails) authentication.getDetails();
        userIPAddress = wad.getRemoteAddress();

        isAuthenticatedByIP = securityService.getGrantedIpAddresses().contains(userIPAddress);


        // Authenticated, the user's IP address matches one in the database
        if (isAuthenticatedByIP) {
            UserDetails user = null;
            UsernamePasswordAuthenticationToken result = null;
            result = new UsernamePasswordAuthenticationToken(
                    "Granted Application",
                    "PlaceholderPWE",
                    Collections.singletonList(new FatGrantedAuthority(Role.ROLE_ADMIN)));

            result.setDetails(authentication.getDetails());

            return result;
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
