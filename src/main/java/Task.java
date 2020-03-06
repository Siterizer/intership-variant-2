import main.entites.post.Post;
import main.entites.user.User;
import main.service.PostService;
import main.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Task {



    public static void main(String args[]){
        run();
    }
    public static void run(){

        List<User> users = UserService.loadUsers();
        List<Post> posts = PostService.loadPosts();
        System.out.println(returnDuplicatePostTitles(posts));
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
    public static String returnDuplicatePostTitles(List<Post> posts){
        Set<String> foundDuplicateTitles = new HashSet<String>();
        String result = "";

        for (Post post : posts) {
            String title = post.getTitle();
            if(foundDuplicateTitles.contains(title)){
                result = result.concat(title + "\n");
            }
            foundDuplicateTitles.add(title);
        }
        return result;
    }
}
