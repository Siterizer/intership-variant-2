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
        List<Post> posts = PostService.loadPosts();
        System.out.println(countUserPosts(users, posts));
    }
    public static String countUserPosts(List<User> users, List<Post> posts){
        String result = "";
        for(User user: users){
            int count =0;
            for(Post post: posts){
                if(user.getId().equals(post.getUserId())){
                    count ++;
                }
            }
            result = result.concat(user.getName() + "napisał(a)" + count + "postów" + "\n");
        }
        return result;
    }
}
