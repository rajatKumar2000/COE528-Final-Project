package coe528.project;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 
 * @author rajat
 */
public class Manager extends Person{
    /**
     * OVERVIEW: Manages the Customers by adding or deleting their related text files.
     *           This is an immutable class
     * 
     * AF(c) = A person as Manager, p, such that:
     * p.username = c.getUsername() = "admin"
     * p.password = c.getPassword() = "admin"
     * p.role = c.getRole() = "manager"
     * 
     * RI(c) = 
     * c.folderPath != null &&
     * c.folderPath != ""
     * 
     */
    
    private String folderPath;
    private PrintWriter writeCustomerFile;

    /**
     * REQUIRES: folderPath must be a valid path to customer data
     * EFFECTS: Creates the manager and gives it access to all customer related files
     * 
     * @param folderPath the path to where the customer files are saved
     */
    public Manager(String folderPath) 
    {
        role = "Manager";
        this.folderPath = folderPath;
    }
    
    /**
     * REQUIRES: username and password must have valid file names. Balance must be greater than or equal to 100
     * EFFECTS: Creates a text file storing information about the new user
     * 
     * @param username is the username of the customer that is added
     * @param password is the password of the customer that is added
     * @param amount is the balance of the customer that is added
     */
    public void addUser(String username, String password, double amount) 
    {
        try 
        {
            writeCustomerFile = new PrintWriter(folderPath + "\\" + username + ".txt");
            writeCustomerFile.println(password);
            writeCustomerFile.println(amount);
            writeCustomerFile.close();
        }
        catch (IOException e) 
        {
            System.out.println("An error occurred."); 
        }  
    }
    
    /**
     * REQUIRES: username must be a currently existing customer username
     * EFFECTS: The text file associated with the deleted customer is deleted
     * 
     * @param username is the username of the customer that is deleted
     */
    public void deleteUser(String username) 
    {
        File deletedFile = new File(folderPath + "\\" + username + ".txt");
        deletedFile.delete();
    }
    
    /**
     * EFFECTS: Implements the abstraction function that was stated alongside the OVERVIEW clause.
     *          Returns a string showing the username, password, and role of this object.
     * 
     * @return the string representation of this Manager object
     */
    @Override
    public String toString() 
    {
        String strManager;
        strManager = "p.username = " + getUsername() + "\n";
        strManager += "p.password = " + getPassword() + "\n";
        strManager += "p.role = " + getRole() + "\n";
        
        return strManager;
    }
    
    /**
     * EFFECTS: Implements the rep invariant that was stated alongside the OVERVIEW clause. 
     *          Returns true if rep is OK, false otherwise.
     * 
     * @return a boolean indicating if the representation of this Manager object is OK
     */
    public boolean repOK() 
    {   
        if (folderPath == null || folderPath.equals(""))
            return false;
        return true;      
    }
    
}
