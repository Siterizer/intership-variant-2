package main.reader;

import main.entitis.post.Post;
import main.entitis.user.User;

import java.util.List;

public interface UserAndPostReader {
    List<User> readUsersFrom(String URL);
    List<Post> readPostsFrom(String URL);
}
