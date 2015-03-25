package controllers;

import java.util.*;

import models.*;
import play.*;

import play.cache.*;
import play.data.validation.*;
import play.libs.Images;
import play.mvc.*;

public class Application extends Controller {

    public static void index() {
       // System.out.println("Good Job");
       // Blog firstBlog = new Blog("learning play", "A framework used in understanding java");
        
        List <Blog> blg = Blog.findAll();
        //b.save();
        render(blg);
    }

    public static void index2(){
    	Post frontPost = Post.find("order by postedAt desc").first();
    	List <Post> olderPosts = Post.find(
    			"order by postedAt desc"
    			).from(1).fetch(10);
    	render(frontPost,olderPosts);
    }
    
    @Before
    static void addDefaults() {
    	
    	//	These info are obtained from application.conf file
        renderArgs.put("blogTitle", Play.configuration.getProperty("blog.title"));
        renderArgs.put("blogBaseline", Play.configuration.getProperty("blog.baseline"));
    }
    
    //Responsible for displaying the various posts
    public static void show(long id){
    	Post post = Post.findById(id);
    	render(post);
    }
    
    //Action to post a Comment
    public static void postComment(Long postId,
    		@Required(message="Author is required") String author,
    		@Required(message="A message is required") String content,
    		@Required(message="Please type the code ") String code,
    		String randomID)
    {
    	Post post = Post.findById(postId);
    	validation.equals(code, Cache.get(randomID)
    			).message("Invalid code. Please type it again");
    	if (validation.hasErrors()) {
    		render("Application/show.html",post,randomID);
    	} 
    	
    	post.addComment(author, content);
    	flash.success("Thanks for posting %s", author);
    	Cache.delete(randomID);
    	show(postId);
    }
    
    //handles captcha to avoid spams     
    public static void captcha(String id) {
    	Images.Captcha captcha = Images.captcha();
    	String code = captcha.getText("#E4EAFD");
    	Cache.set(id, code , "10mn");
    	renderBinary(captcha);
    }
    
    //	Tags stuffs 
    public static void listTagged(String tag) {
        List <Post> posts = Post.findTaggedWith(tag);
        render(tag, posts);
    }
    
    //This is for my assignment on model
    
    public static void addEntry(@Required String newTitle,@Required String newContent) {
	   if(validation.hasErrors()){
		flash.error("Oops, please enter the required Entry!");
		index();
	   }
    	Blog newblg = new Blog(newTitle,newContent);
    	newblg.save(); // Very important because it  commits the changes  to the database
    render(newblg);
    }
    
}