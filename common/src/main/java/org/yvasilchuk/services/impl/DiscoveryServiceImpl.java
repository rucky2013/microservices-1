package org.yvasilchuk.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.yvasilchuk.services.DiscoveryService;

import java.util.List;

@Service
public class DiscoveryServiceImpl implements DiscoveryService {
    @Autowired
    private DiscoveryClient discoveryClient;

    @Override
    public String getDbServerUrl() {
        List<ServiceInstance> db = discoveryClient.getInstances("db");
        if (db != null && !db.isEmpty()) {
            return db.get(0).getUri().toString();
        }
        return null;
    }
}
