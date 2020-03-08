package library;

import library.entities.post.Post;
import library.entities.user.Address;
import library.entities.user.Geo;
import library.entities.user.User;
import library.reader.JSONReader;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class UserAndPostTest {

    private static UserAndPost userAndPost;
    private  static List<User> users;
    private  static List<Post> posts;

    @BeforeClass
    public static void init()
    {
        userAndPost = new UserAndPost();
        users = new ArrayList<>();
        posts = new ArrayList<>();
        userAndPost.setUsers(users);
        userAndPost.setPosts(posts);
    }
    @Before
    public void clearLists(){
        users.clear();
        posts.clear();
    }

    @Test(expected=IllegalArgumentException.class)
    public void illegalULR(){
        UserAndPost userAndPost = new UserAndPost("", "");
    }

    @Test
    public void countUserPosts1() {
        assertEquals(0, userAndPost.countUserPosts().size());
    }
    @Test
    public void countUserPosts2() {
        User user1 = new User(); user1.setId(Long.parseLong("1"));user1.setName("Name");
        users.add(user1);
        Post post1 = new Post();post1.setUserId(Long.parseLong("1"));
        Post post2 = new Post();post2.setUserId(Long.parseLong("1"));
        Post post3 = new Post();post3.setUserId(Long.parseLong("1"));
        posts.addAll(Arrays.asList(post1, post2, post3));
        assertEquals("Name" + " napisał(a) " + 3 + " postów", userAndPost.countUserPosts().get(0));
    }
    @Test
    public void countUserPosts3() {
        User user1 = new User(); user1.setId(Long.parseLong("2"));user1.setName("Name");
        users.add(user1);
        Post post1 = new Post();post1.setUserId(Long.parseLong("1"));
        Post post2 = new Post();post2.setUserId(Long.parseLong("2"));
        Post post3 = new Post();post3.setUserId(Long.parseLong("3"));
        posts.addAll(Arrays.asList(post1, post2, post3));
        assertEquals("Name" + " napisał(a) " + 1 + " postów", userAndPost.countUserPosts().get(0));
    }

    @Test
    public void returnDuplicatePostTitles1() {
        assertEquals(0, userAndPost.returnDuplicatePostTitles().size());
    }

    @Test
    public void returnDuplicatePostTitles2() {
        Post post1 = new Post();post1.setTitle("1");
        Post post2 = new Post();post2.setTitle("2");
        Post post3 = new Post();post3.setTitle("3");
        posts.addAll(Arrays.asList(post1, post2, post3));
        assertEquals(0, userAndPost.returnDuplicatePostTitles().size());
    }

    @Test
    public void returnDuplicatePostTitles3() {
        Post post1 = new Post();post1.setTitle("1");
        Post post2 = new Post();post2.setTitle("1");
        Post post3 = new Post();post3.setTitle("2");
        posts.addAll(Arrays.asList(post1, post2, post3));
        assertEquals("1", userAndPost.returnDuplicatePostTitles().get(0));
    }

    @Test
    public void findTheClosestUserForAllUsers1() {
        assertEquals(0, userAndPost.findTheClosestUserForAllUsers().size());
    }

    @Test
    public void findTheClosestUserForAllUsers2() {
        Geo geo1 = new Geo(); geo1.setLat("1.00"); geo1.setLng("1.00");
        Address address1 = new Address();address1.setGeo(geo1);
        Geo geo2 = new Geo(); geo2.setLat("1.50"); geo2.setLng("1.50");
        Address address2 = new Address();address2.setGeo(geo2);
        Geo geo3 = new Geo(); geo3.setLat("0.70"); geo3.setLng("1.70");
        Address address3 = new Address();address3.setGeo(geo3);
        User user1 = new User(); user1.setId(Long.parseLong("1"));user1.setAddress(address1);
        User user2 = new User(); user2.setId(Long.parseLong("2"));user2.setAddress(address2);
        User user3 = new User(); user3.setId(Long.parseLong("3"));user3.setAddress(address3);
        users.addAll(Arrays.asList(user1, user2, user3));
        List<String> results = userAndPost.findTheClosestUserForAllUsers();
        assertEquals("The closest resident User with id: (id) = " + 1 + " is User with id: (id) = " + 2,
                results.get(0));
        assertEquals("The closest resident User with id: (id) = " + 2 + " is User with id: (id) = " + 1,
                results.get(1));
        assertEquals("The closest resident User with id: (id) = " + 3 + " is User with id: (id) = " + 1,
                results.get(2));
    }
}