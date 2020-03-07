package library;

import library.entities.post.Post;
import library.reader.JSONReader;
import library.entities.user.Geo;
import library.entities.user.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserAndPost {
    private List<User> users;
    private List<Post> posts;

    // Constructor is the solution to the first task //
    //" pobierze dane o postach z https://jsonplaceholder.typicode.com/posts i połączy je z danymi o
    //userach https://jsonplaceholder.typicode.com/users"

    public UserAndPost(String usersURL, String postsURL){
        JSONReader jsonReader = new JSONReader();
        this.users = jsonReader.readUsersFrom(usersURL);
        this.posts = jsonReader.readPostsFrom(postsURL);
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
            result = result.concat(user.getName() + " napisał(a) " + count + " postów" + "\n");
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
        for(User user : users){
            User theClosestUser = findTheClosest(user);
            result = result.concat("The closest resident User with id: (id) = " + user.getId() +
                    " is User with id: (id) = " + theClosestUser.getId() + "\n");
        }
        return result;
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
