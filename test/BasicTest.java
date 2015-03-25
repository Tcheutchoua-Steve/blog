import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class BasicTest extends UnitTest {
    
    @Before
    //Frees the db before loading a new one
    public void setup(){
        Fixtures.deleteDatabase();
    }

    @Test
    public void createAndRetrieveUser() {
        //CReate a new user and save it
        new User("tcsalist@yahoo.com","secret","Steve").save();
        
        //Retrieve the user with e-mail address tcsalist@yahoo.com
        User Steve = User.find("byEmail","tcsalist@yahoo.com").first();
    
        // Test
        assertNotNull(Steve);
        assertEquals("Steve",Steve.fullname);
    }   
        
    @Test
    public void tryConnectAsUser(){
        //Create a new user and save it
        new User("tcsalist@yahoo.com","secrete","Steve").save();
        
        //Test
        assertNotNull(User.connect("tcsalist@yahoo.com", "secrete"));
        assertNull(User.connect("tcsalist@yahoo.com", "badpassword"));
        assertNull(User.connect("tom@yahoo.com", "secrete"));
    }
    
    @Test
    public void createPost(){
        //Create a new user and save it 
        User steve = new User("steve@gmail.com","secret","Steve").save();
        
        //Create a new post
        new Post(steve,"My first post","Hello world").save();
        
        //  Test that the post has been created
        assertEquals(1,Post.count());
        
        //  Retrieve all posts created by Steve
        List <Post> stevePosts = Post.find("byAuthor", steve).fetch();
        
        //  Tests
        assertEquals(1,stevePosts.size());
        Post firstPost = stevePosts.get(0);
        assertNotNull(firstPost);
        assertEquals("My first post",firstPost.title);
        assertEquals("Hello world",firstPost.content);
        assertNotNull(firstPost.postedAt);
    }
    
    @Test
    public void postComments(){
        //Create a new user and save it
        User steve = new User("steve@gmail.com","secrete","Steve").save();
        
        // Create a  new post
        Post stevePost = new Post(steve,"My first post","Hello world").save();
        
        //  Post a fist comment
        new Comment(stevePost,"Damien","Nice post").save();
        new Comment(stevePost,"Martin","I knew that !").save();
        
        //Retrieve all comments
        List <Comment> stevePostComments = Comment.find("byPost", stevePost).fetch();
        
        //Test
        assertEquals(2,stevePostComments.size());
        
        Comment firstComment = stevePostComments.get(0);
        assertNotNull(firstComment);
        assertEquals("Damien",firstComment.author);
        assertEquals("Nice post",firstComment.content);
        assertNotNull(firstComment.postedAt);
        
        Comment secondComment = stevePostComments.get(1);
        assertNotNull(secondComment);
        assertEquals("Martin",secondComment.author);
        assertEquals("I knew that !",secondComment.content);
        assertNotNull(secondComment.postedAt);
    }
    @Test
    public void useTheCommentsRelation(){
        //Create a new user and save it
        User steve = new User("steve@gmail.com","secret","Steve").save();
        
        //Create a new post
        Post stevePost = new Post(steve,"My first post","Hello world").save();
        
        //  Post a first comment
        stevePost.addComment("Damien", "Nice Post");
        stevePost.addComment("Martin", "I knew that !");
        
        //  count things
        assertEquals(1,User.count());
        assertEquals(1,Post.count());
        assertEquals(2,Comment.count());
        
        //  Retrieve Steve's post
        stevePost = Post.find("byAuthor", steve).first();
        assertNotNull(stevePost);
        
        //  Navigate to comments
        assertEquals(2,stevePost.comments.size());
        assertEquals("Damien",stevePost.comments.get(0).author);
        
        //Delete the post
        stevePost.delete();
        
        // Check that all comments have been deleted
        assertEquals(1,User.count());
        assertEquals(0,Post.count());
        assertEquals(0,Comment.count());
        
        
    }
         
    @Test
    public void createBlog(){

        
        //Create a new Blog
        new Blog("My first Blog","Hello world").save();
        
        //  Test that the Blob has been created
        assertEquals(1,Blog.count());
        
        //  Retrieve all Blobts created by Steve
        List <Blog> steveBlogs = Blog.find("byTittle","My first Blog").fetch();
        
        //  Tests
        assertEquals(1,steveBlogs.size());
        Blog firstBlog = steveBlogs.get(0);
        assertNotNull(firstBlog);
        assertEquals("My first Blog",firstBlog.title);
        assertEquals("Hello world",firstBlog.content);
        assertNotNull(firstBlog.postedAt);
    }
    
    @Test
    public void testTags() {
    	//	Create a new user and save it
    	User bob = new User("bob@gmail.com", "secret","Bob").save();
    	
    	//	Create a new post
    	Post bobPost = new Post(bob, "My first post", "Hello world").save();
    	Post anotherBobPost = new Post(bob, "Hop","Hello world").save();
    	
    	//	Well
    	assertEquals(0,Post.findTaggedWith("Red").size());
    	
    	//	Tag it now
    	bobPost.tagItWith("Red").tagItWith("Blue").save();
    	anotherBobPost.tagItWith("Red").tagItWith("Green").save();
    	
    	//	Check
    	assertEquals(2, Post.findTaggedWith("Red").size());
    	assertEquals(1, Post.findTaggedWith("Blue").size());
    	assertEquals(1, Post.findTaggedWith("Green").size());

    	List<Map> cloud = Tag.getCloud();
    	assertEquals(
    	    "[{tag=Blue, pound=1}, {tag=Green, pound=1}, {tag=Red, pound=2}]",
    	    cloud.toString()
    	);
    }
}
