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

@Entity     //This means that the class accesses the database
public class Blog extends Model { //extend Model provides JPA helpers
    public String title;
    public Date postedAt;
    
    @Lob
    public String content;
    
    public Blog(String title, String content){
        this.title = title;
        this.content = content;
        this.postedAt = new Date();
    }
}
