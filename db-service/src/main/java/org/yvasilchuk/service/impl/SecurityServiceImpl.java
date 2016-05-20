package org.yvasilchuk.service.impl;

import org.springframework.stereotype.Service;
import org.yvasilchuk.service.SecurityService;

import java.util.Collections;
import java.util.List;

@Service
public class SecurityServiceImpl implements SecurityService {
    @Override
    public List<String> getGrantedIpAddresses() {
        return Collections.singletonList("127.0.0.1");
    }
}
