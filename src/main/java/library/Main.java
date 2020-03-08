package library;

public class Main {
    public static void main(String args[]){
        UserAndPost userAndPost = new UserAndPost("https://jsonplaceholder.typicode.com/users", "https://jsonplaceholder.typicode.com/posts");
        System.out.println(userAndPost.countUserPosts());

    }
}
