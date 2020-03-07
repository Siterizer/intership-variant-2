package library.reader;

import com.google.gson.Gson;
import library.entitis.post.Post;
import library.entitis.user.User;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class JSONReader implements UserAndPostReader {

    private RestTemplate restTemplate;
    private Gson gson;

    public JSONReader(){
        restTemplate = new RestTemplate();
        gson = new Gson();
    }

    @Override
    public List<User> readUsersFrom(String URL) {
        String string = restTemplate.getForObject(URL, String.class);
        User[] usersArray = gson.fromJson(string, User[].class);
        return Arrays.asList(usersArray);
    }

    @Override
    public List<Post> readPostsFrom(String URL) {
        String string = restTemplate.getForObject(URL, String.class);
        Post[] postsArray = gson.fromJson(string, Post[].class);
        return Arrays.asList(postsArray);
    }
}
