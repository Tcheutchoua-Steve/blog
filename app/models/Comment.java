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

import play.data.validation.*;
import play.db.jpa.*;

@Entity
public class Comment extends Model {
	
	@Required
    public String author;
	
	@Required
    public Date postedAt;
    
    @Lob
    @Required
    @MaxSize(10000)
    public String content;
    
    @ManyToOne
    @Required
    public Post post;
    
    public Comment(Post post, String author,String content){
        this.post = post;
        this.author = author;
        this.content = content;
        this.postedAt = new Date();
    }
    
    
}