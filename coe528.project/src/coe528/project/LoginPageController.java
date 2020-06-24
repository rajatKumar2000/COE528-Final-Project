package coe528.project;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author rajat
 */
public class LoginPageController implements Initializable {
   
    @FXML private TextField txtUsername;
    @FXML private PasswordField txtPassword;
    @FXML private Label lblWrongLogin;
    
    private ArrayList<String> allUsernames = new ArrayList<>();
    private String userFilePath = ".\\src\\coe528\\project\\UserInformation";
    
   @FXML private void HandleSignInClick(ActionEvent event) 
    {        
        if (txtUsername.getText().equals("admin") && txtPassword.getText().equals("admin")) //For the manager
        {
            try 
            {
                Manager theManager = new Manager(userFilePath);
                
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("ManagerUI.fxml"));
                Parent ManagerViewParent = loader.load();
                Scene ManagerViewScene = new Scene(ManagerViewParent);
                    
                ManagerUIController controller = loader.getController();
                controller.setManagerDetails(theManager);
                
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(ManagerViewScene);
                window.show();
            }
            catch (IOException e) 
            { 
                System.out.println("An error occurred."); 
            }  
        }
        else if (allUsernames.contains(txtUsername.getText())) 
        {
            System.out.println("Valid username");
  
            try 
            { 
                File readFile = new File(userFilePath + "\\" + txtUsername.getText() + ".txt");
                Scanner reader = new Scanner(readFile);
                
                String password = reader.nextLine();
                double balance = Double.parseDouble(reader.nextLine());
                
                reader.close();
                
                if (password.equals(txtPassword.getText())) 
                {
                    System.out.println("Valid Password");
                    lblWrongLogin.setVisible(false);
                    
                    Customer newCustomer = new Customer(txtUsername.getText(),password,balance,readFile.getPath());
                    
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("CustomerUI.fxml"));
                    Parent customerViewParent = loader.load();
                    Scene customerViewScene = new Scene(customerViewParent);
                    
                    CustomerUIController controller = loader.getController();
                    controller.setCustomerDetails(newCustomer);
                    
                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(customerViewScene);
                    window.show();
                }
                else 
                {
                    lblWrongLogin.setVisible(true);
                    System.out.println("Invalid Password");
                }
            } 
            catch (IOException e) 
            { 
                System.out.println("An error occurred."); 
            } 
        }
        else 
           lblWrongLogin.setVisible(true);
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {       
       File folder = new File(userFilePath); //Using src, so might have issues with build
       
       String[] pathnames = folder.list();
       
        for (int i = 0; i < pathnames.length; i++) {
            String properName = pathnames[i].substring(0, pathnames[i].length() - 4); //Removes '.txt'
            allUsernames.add(properName);
        }
    }    
    
}
