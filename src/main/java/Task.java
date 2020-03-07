import main.UserAndPost;

public class Task {



    public static void main(String args[]){
        UserAndPost userAndPost = new UserAndPost();
        System.out.println(userAndPost.countUserPosts());
        System.out.println(userAndPost.findTheClosestUserForAllUsers());
        System.out.println(userAndPost.returnDuplicatePostTitles());
    }
}
