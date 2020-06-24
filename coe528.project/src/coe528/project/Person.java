package coe528.project;

/**
 *
 * @author rajat
 */
public abstract class Person {
    protected String username;
    protected String password;
    protected String role;
    
    public String getUsername() 
    {
        return username;
    }
    
    public String getPassword() 
    {
        return password;
    }  
    
    public String getRole() 
    {
        return role;
    }
}
