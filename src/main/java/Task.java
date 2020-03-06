import main.entites.User;
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
    }
}
