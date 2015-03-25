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
public class User extends Model {
    
	@Email
    @Required
    public String email;
	
	@Required
    public String password;
	
    public String fullname;
    public boolean isAdmin;
    
    public User(String email, String password,String fullname){
        this.email = email;
        this.password = password;
        this.fullname = fullname;
    }
    
    public String toString() {
        return email;
    }
    
    public static User connect(String email, String password){
        return find("byEmailAndPassword",email,password).first();
    }
}

