package library;

import library.entities.post.Post;
import library.reader.JSONReader;
import library.entities.user.Geo;
import library.entities.user.User;
import library.reader.UserAndPostReader;

import java.util.*;
import java.util.stream.Collectors;

public class UserAndPost {
    private List<User> users;
    private List<Post> posts;

    // Constructor is the solution to the first task //
    //" pobierze dane o postach z https://jsonplaceholder.typicode.com/posts i połączy je z danymi o
    //userach https://jsonplaceholder.typicode.com/users"

    public UserAndPost(String usersURL, String postsURL){
        checkURL(usersURL);
        checkURL(postsURL);
        UserAndPostReader reader = new JSONReader();
        this.users = reader.readUsersFrom(usersURL);
        this.posts = reader.readPostsFrom(postsURL);
    }
    public UserAndPost(){
        this.users = new ArrayList<>();
        this.posts = new ArrayList<>();
    }

    // Second task:
    // "policzy ile postów napisali userzy i zwróci listę
    // stringów w postaci “user_name napisał(a) count postów"
    public List<String> countUserPosts(){
        return(users.stream()
                .map(user ->
                    {long count = posts
                            .stream()
                            .filter(post -> post.getUserId().equals(user.getId()))
                            .count();
                    return user.getName() + " napisał(a) " + count + " postów";
                })
                .collect(Collectors.toList()));
    }
    // Third task:
    // "sprawdzi czy tytuły postów są unikalne i zwróci listę tytułów które nie są."
    public List<String> returnDuplicatePostTitles(){
        Set<String> foundDuplicateTitles = new HashSet<>();

        return posts.stream()
                .filter(post -> !foundDuplicateTitles.add(post.getTitle()))
                .map(Post::getTitle)
                .collect(Collectors.toList());
    }

    //Fourth task:
    // "dla każdego użytkownika znajdzie innego użytkownika, który mieszka najbliżej niego"
    public List<String>  findTheClosestUserForAllUsers(){
        return (users.stream()
                .map(s -> "The closest resident User with id: (id) = " + s.getId() +
                        " is User with id: (id) = " + findTheClosest(s).getId())
                .collect(Collectors.toList()));
    }

    private User findTheClosest(User user){
        double max = Double.MAX_VALUE;
        User result = new User();
        for(User arrayUser : users){
            if(user.equals(arrayUser)){
                continue;
            }
            double distanceBetweenUsers = calculateDistance(user.getAddress().getGeo(), arrayUser.getAddress().getGeo());
            if(distanceBetweenUsers < max){
                max = distanceBetweenUsers;
                result = arrayUser;
            }
        }
        return result;
    }
    private double calculateDistance(Geo geo1, Geo geo2){
        return Math.hypot(Double.parseDouble(geo1.getLat()) - Double.parseDouble(geo2.getLat()),
                Double.parseDouble(geo1.getLng()) - Double.parseDouble(geo2.getLng()));
    }

    private void checkURL(String URL) {
        if(URL.equals("")){
            throw new IllegalArgumentException("URL can't be empty");
        }
    }

    // GETTERS  & SETTERS//
    public List<User> getUsers() {
        return users;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
    // GETTERS  & SETTERS//
}
