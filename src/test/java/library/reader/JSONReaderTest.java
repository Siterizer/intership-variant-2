package library.reader;

import library.entitis.post.Post;
import library.entitis.user.User;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class JSONReaderTest {

    private  static List<User> users;
    private  static List<Post> posts;

    @BeforeClass
    public static void readUsersFrom() {
        JSONReader reader = new JSONReader();
        users = reader.readUsersFrom("https://jsonplaceholder.typicode.com/users");
        posts = reader.readPostsFrom("https://jsonplaceholder.typicode.com/posts");
    }

    @Test
    public void CheckPresenceOfObjects(){
        for(User user : users){
            assertNotNull(user);
            assertNotNull(user.getAddress());
            assertNotNull(user.getCompany());
            assertNotNull(user.getAddress().getGeo());
        }
        for(Post post : posts){
            assertNotNull(post);
        }
    }

    @Test
    public void checkIfAllFieldsHaveValues(){
        for(User user : users){
            assertNotNull(user.getId());
            assertNotNull(user.getName());
            assertNotNull(user.getEmail());
            assertNotNull(user.getPhone());
            assertNotNull(user.getUsername());
            assertNotNull(user.getWebsite());
            assertNotNull(user.getAddress().getCity());
            assertNotNull(user.getAddress().getStreet());
            assertNotNull(user.getAddress().getSuite());
            assertNotNull(user.getAddress().getZipcode());
            assertNotNull(user.getAddress().getGeo().getLat());
            assertNotNull(user.getAddress().getGeo().getLng());
            assertNotNull(user.getCompany().getBs());
            assertNotNull(user.getCompany().getCatchPhrase());
            assertNotNull(user.getCompany().getName());
        }
        for(Post post : posts){
            assertNotNull(post.getUserId());
            assertNotNull(post.getTitle());
            assertNotNull(post.getBody());
            assertNotNull(post.getId());
        }
    }

    @Test
    public void checkIfUserExistsInPosts(){
        Set<Long> userIds = new HashSet<>();
        for(User user : users){
            userIds.add(user.getId());
        }
        for(Post post: posts){
            assertTrue(userIds.contains(post.getUserId()));
        }
    }
}