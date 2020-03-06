package main.service;

import com.google.gson.Gson;
import main.entites.post.Post;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class PostService {
    public static List<Post> loadPosts(){
        RestTemplate restTemplate = new RestTemplate();
        String string = restTemplate.getForObject(
                "https://jsonplaceholder.typicode.com/posts", String.class);
        Gson gson = new Gson();
        Post[] postsArray = gson.fromJson(string, Post[].class);
        return Arrays.asList(postsArray);
    }
}
