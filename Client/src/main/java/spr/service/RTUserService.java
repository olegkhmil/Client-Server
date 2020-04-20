package spr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import spr.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RTUserService implements UserService {


    private RestTemplate restTemplate;

    @Autowired
    public RTUserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public List<User> getAllUsers() {
        String url = "http://localhost:8082/rest/all";
        return restTemplate.getForObject(url, List.class);
    }

    @Override
    public User getUserById(Long id) {
        String url = "http://localhost:8082/rest/userById/{id}";
        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(id));
        return restTemplate.getForObject(url, User.class, params);
    }

    @Override
    public User getUserByName(String userName) {
        String url = "http://localhost:8082/rest/user/{userName}";
        Map<String, String> params = new HashMap<>();
        params.put("userName", userName);
        return restTemplate.getForObject(url, User.class, params);
    }

    @Override
    public boolean addUser(User user) {
        String url = "http://localhost:8082/rest/add";
        User user1 = restTemplate.postForObject(url, user, User.class);
        return user1 != null;
    }

    @Override
    public void deleteUser(Long id) {
        String url = "http://localhost:8082/rest/delete/{id}";
        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(id));
        restTemplate.delete(url, params);
    }

    @Override
    public User updateUser(User user) {
        String url = "http://localhost:8082/rest/update";
        User user1 = restTemplate.postForObject(url, user, User.class);
        return user1;
    }
}
