package library.reader;

import library.entitis.post.Post;
import library.entitis.user.User;

import java.util.List;

public interface UserAndPostReader {
    List<User> readUsersFrom(String URL);
    List<Post> readPostsFrom(String URL);
}
