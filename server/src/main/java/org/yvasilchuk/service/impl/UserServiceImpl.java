package org.yvasilchuk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.yvasilchuk.domain.entity.User;
import org.yvasilchuk.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private DiscoveryClient discoveryClient;

    @Override
    public List<String> getServices() {
        return discoveryClient.getServices();
    }

    @Override
    public String getDbServerUrl() {
        List<ServiceInstance> db = discoveryClient.getInstances("db");
        if (db != null && !db.isEmpty()) {
            return db.get(0).getUri().toString();
        }
        return null;
    }

    @Override
    public User getUserById(Integer id) {
        RestTemplate template = new RestTemplate();
        User user = template.getForObject(getDbServerUrl() + "/api/user/" + id, User.class);
        return user;
    }
}
