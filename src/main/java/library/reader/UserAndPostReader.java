package library.reader;

import library.entities.post.Post;
import library.entities.user.User;

import java.util.List;

public interface UserAndPostReader {
    List<User> readUsersFrom(String URL);
    List<Post> readPostsFrom(String URL);
}
