import main.entites.post.Post;
import main.entites.user.User;
import main.service.PostService;
import main.service.UserService;

import java.util.List;

public class Task {
    public static void main(String args[]){
        run();
    }
    public static void run(){

        List<User> users = UserService.loadUsers();
        for (User user : users) {
            System.out.println(user);
        }
        List<Post> posts = PostService.loadPosts();
        for (Post post : posts) {
            System.out.println(post);
        }
    }
}
