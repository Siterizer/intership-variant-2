package main.service;

import com.google.gson.Gson;
import main.entites.User;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class UserService {
    public static List<User> loadUsers(){
        RestTemplate restTemplate = new RestTemplate();
        String string = restTemplate.getForObject(
                "https://jsonplaceholder.typicode.com/users", String.class);
        Gson gson = new Gson();
        User[] usersArray = gson.fromJson(string, User[].class);
        return Arrays.asList(usersArray);
    }
}
