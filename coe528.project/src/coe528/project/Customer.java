package coe528.project;

import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author rajat
 */
public class Customer extends Person{
    
    private double balance;
    private PrintWriter writeCustomerFile;
    private String customerFilePath;
    private Level customerLevel;
    
    public Customer(String username, String password, double balance,String customerFilePath){
        role = "Customer";
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.customerFilePath = customerFilePath;
        checkLevel();
    }
    
    public double getBalance() 
    {
        return balance;
    }
    
    public void withdraw(double money) 
    {
        balance -= money;
        checkLevel();
        updateWrittenBalance();
    }
    
    public void deposit(double money) 
    {
        balance += money;
        checkLevel();
        updateWrittenBalance();
    }
    
    private void updateWrittenBalance()
    {
        try 
        {
            writeCustomerFile = new PrintWriter(customerFilePath);
            writeCustomerFile.println(getPassword());
            writeCustomerFile.println(getBalance());
            writeCustomerFile.close();
        }
        catch (IOException e) 
        {
            System.out.println("An error occurred."); 
        }       
    }
    
    private void checkLevel() 
    {
        if (getBalance() < 10000) 
            customerLevel = new SilverLevel();
        else if (getBalance() < 20000)
            customerLevel = new GoldLevel();
        else
            customerLevel = new PlatinumLevel();   
    }
    
    public int getLevelFee() 
    {
        return customerLevel.levelFee();
    }
    
    public String getLevelName() 
    {
        return customerLevel.levelName();
    }
}
