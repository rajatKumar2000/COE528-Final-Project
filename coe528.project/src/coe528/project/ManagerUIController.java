package coe528.project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rajat
 */
public class ManagerUIController implements Initializable {
    
    @FXML private TextField txtAddUsername;
    @FXML private TextField txtAddPassword;
    @FXML private TextField txtAddBalance;
    @FXML private Label lblValid;
    @FXML private TextField txtDeleteUsername;
    
    private Manager theManager;
    
    @FXML private void HandleLogout(ActionEvent event) throws IOException
    {
        Parent loginViewParent = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        Scene loginViewScene = new Scene(loginViewParent);
                    
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(loginViewScene);
        window.show();
    }
    
    @FXML private void HandleAdd() 
    {
        if (checkValidAddInput()) {
            double amount = Double.parseDouble(txtAddBalance.getText());
            if (amount >= 100) {
                if (!txtAddUsername.getText().equals("") && !txtAddPassword.getText().equals("")) {
                    theManager.addUser(txtAddUsername.getText(), txtAddPassword.getText(), amount);
                    lblValid.setText("Customer Created!");
                }
                else
                    lblValid.setText("Username and password must be non-empty");  
            }
            else
                lblValid.setText("Amount must be >= 100");
        }
        else
            lblValid.setText("Amount must be of type double");
    }
    
    @FXML private void HandleDelete() 
    {
        if (!txtDeleteUsername.getText().equals("")) {
            String bruv = txtDeleteUsername.getText();
            theManager.deleteUser(txtDeleteUsername.getText());
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }  
    
    public void setManagerDetails(Manager theManager) 
    {
        this.theManager = theManager;
    }
    
    private boolean checkValidAddInput() 
    {
        try 
        {
            double amount = Double.parseDouble(txtAddBalance.getText());
            return true;
        }
        catch(Exception e) 
        {
            System.out.println("Invalid input for amount entered");
            return false;
        }
    }
}
