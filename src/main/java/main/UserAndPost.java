package main;

import main.entites.post.Post;
import main.entites.user.User;
import main.service.DivideAndConquerAlgorithm;
import main.service.PostService;
import main.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserAndPost {

    public void run(){

        List<User> users = UserService.loadUsers();
        List<Post> posts = PostService.loadPosts();
        System.out.println(findTheClosestUserForAllUsers(users));
    }
    public String countUserPosts(List<User> users, List<Post> posts){
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
    public String returnDuplicatePostTitles(List<Post> posts){
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
    public String  findTheClosestUserForAllUsers(List<User> users){
        String result = "";
        DivideAndConquerAlgorithm divideAndConquerAlgorithm = new DivideAndConquerAlgorithm(users);
        for(User user : users){
            User theClosestUser = divideAndConquerAlgorithm.findTheClosest(user);
            result = result.concat("The closest resident User with id: (id) = " + user.getId() + " is User with id: (id) = " + theClosestUser.getId() + "\n");
        }
        return result;
    }
}
