package main.service;

import main.entites.post.Post;
import main.entites.user.User;
import main.service.FindTheClosestUserAlgorithm;
import main.service.PostService;
import main.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserAndPost {
    private List<User> users;
    private List<Post> posts;

    // Constructors are the solution to the first task //
    //" pobierze dane o postach z https://jsonplaceholder.typicode.com/posts i połączy je z danymi o
    //userach https://jsonplaceholder.typicode.com/users"

    public UserAndPost(String userURL, String postURL){
        this.users = UserService.loadUsersFrom(userURL);
        this.posts = PostService.loadPostsFrom(postURL);
    }

    public UserAndPost(){
        this.users = UserService.loadUsersFrom("https://jsonplaceholder.typicode.com/users");
        this.posts = PostService.loadPostsFrom("https://jsonplaceholder.typicode.com/posts");
    }

    // Second task:
    // "policzy ile postów napisali userzy i zwróci listę
    // stringów w postaci “user_name napisał(a) count postów"
    public String countUserPosts(){
        String result = "";
        for(User user: users){
            int count =0;
            for(Post post: posts){
                if(user.getId().equals(post.getUserId())){
                    count ++;
                }
            }
            result = result.concat(user.getName() + "napisał(a) " + count + " postów" + "\n");
        }
        return result;
    }
    // Third task:
    // "sprawdzi czy tytuły postów są unikalne i zwróci listę tytułów które nie są."
    public String returnDuplicatePostTitles(){
        Set<String> foundDuplicateTitles = new HashSet<>();
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

    //Fourth task:
    // "dla każdego użytkownika znajdzie innego użytkownika, który mieszka najbliżej niego"
    public String  findTheClosestUserForAllUsers(){
        String result = "";
        FindTheClosestUserAlgorithm findTheClosestUserAlgorithm = new FindTheClosestUserAlgorithm(users);
        for(User user : users){
            User theClosestUser = findTheClosestUserAlgorithm.findTheClosest(user);
            result = result.concat("The closest resident User with id: (id) = " + user.getId() +
                    " is User with id: (id) = " + theClosestUser.getId() + "\n");
        }
        return result;
    }

    // GETTERS //
    public List<User> getUsers() {
        return users;
    }

    public List<Post> getPosts() {
        return posts;
    }
    // GETTERS //
}
