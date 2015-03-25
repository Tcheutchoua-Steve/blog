 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

/**
 *
 * @author tcheutchoua Steve
 */
import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;
import play.data.validation.*;

@Entity
public class Post extends Model{
	
	@Required
    public String title;
	
	@Required
    public Date postedAt;
    
    @Lob
    @Required
    @MaxSize(10000)
    public String content;
    
    @Required
    @ManyToOne
    public User author;
    
    @ManyToMany(cascade=CascadeType.PERSIST)
    public Set<Tag> tags;
    
    @OneToMany(mappedBy="post",cascade=CascadeType.ALL)
    public List<Comment> comments;
    
    public Post(User author, String title, String content){
        this.comments = new ArrayList<Comment>();
        this.author = author;
        this.title = title;
        this.content = content;
        this.postedAt = new Date();
    }
    
    public Post addComment(String author, String content){
        Comment newComment = new Comment(this,author,content).save();
        this.comments.add(newComment);
        this.save();
        return this;
        
    }
    
    //Enabling pagination with the next two methods
    
    public Post previous(){
    	return Post.find("postedAt < ? order by postedAt desc", postedAt ).first();
    }
    
    public Post next(){
    	return Post.find("postedAt > ? order by postedAt asc", postedAt).first();
    }
    
    //	Tagging a new post
    public Post tagItWith(String name) {
    	tags.add(Tag.findOrCreateByName(name));
    	return this;
    }
    
    // Retrieving posts from a specific tag
    public static List<Post> findTaggedWith(String tag){
    	return Post.find("select distinct p from Post p join p.tags as t where t.name = ?", tag
    			).fetch();
    }
    
    public static List<Post> findTaggedWith(String... tags){
    	return Post.find(
    			"select distinct p from Post p join P.tags as t where t.name in (:tags) group by p.id, p.author, p.title, p.content,p.postedAt having count(t.id) = :size" 
    			).bind("tags", tags).bind("size", tags.length).fetch();
    }
    //The tricky part is that we have to use a having count statement to filter only posts that have exactly all tags from the joined view
    //Note that we cannot use the Post.find("…", tags, tags.count) signature here, because tags is already a vararg.
}
    
    
