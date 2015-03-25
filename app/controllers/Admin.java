package controllers;
 
import play.*;
import play.mvc.*;
 
import java.util.*;
 
import models.*;
 
@With(Secure.class)
public class Admin extends Controller {
    
    @Before
    static void setConnectedUser() {
        if(Security.isConnected()) {
            User user = User.find("byEmail", Security.connected()).first();
            renderArgs.put("user", user.fullname);
        }
    }
 
    //Finds the all the posts of the admin user
    public static void index() {
    	String user = Security.connected();
        List<Post> posts = Post.find("author.email", user).fetch();
        render(posts);
    }
    
    // create a new form 
    public static void form(Long id) {
    	if (id != null) {
			Post post = Post.findById(id);
			render(post);
		}
    	render();
    }
    
    // saves a newly created form
    public static void save(String title, String content, String tags) {
        // Create post
        User author = User.find("byEmail", Security.connected()).first();
        Post post = new Post(author, title, content);
        // Set tags list
        for(String tag : tags.split("\\s+")) {
            if(tag.trim().length() > 0) {
                post.tags.add(Tag.findOrCreateByName(tag));
            }
        }
        // Validate
        validation.valid(post);
        if(validation.hasErrors()) {
            render("@form", post);
        }
        // render("@form") as a shortcut for render("Admin/form.html"). It just tells Play to 
        // Save
        post.save();
        index();
    }
}