package org.yvasilchuk.service;

import java.util.List;

public interface SecurityService {
    List<String> getGrantedIpAddresses();
}
