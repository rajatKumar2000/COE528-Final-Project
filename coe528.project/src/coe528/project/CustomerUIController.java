package coe528.project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rajat
 */
public class CustomerUIController implements Initializable {

    @FXML private Label lblUsername;
    @FXML private Label lblBalance;
    @FXML private Label lblLevel;
    @FXML private Label lblPurchasePaintings;
    @FXML private TextField txtMoneyChange;
    private Customer theCustomer;
    
    @FXML private void HandleLogout(ActionEvent event) throws IOException
    {
        Parent loginViewParent = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        Scene loginViewScene = new Scene(loginViewParent);
                    
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(loginViewScene);
        window.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
    public void setCustomerDetails(Customer theCustomer) 
    {
        this.theCustomer = theCustomer;
        lblUsername.setText("  User: " + theCustomer.getUsername());
        updateDisplayInfo();
    }
    
    @FXML private void HandleBuy50() 
    {
        int money = 50 + theCustomer.getLevelFee();
        if (theCustomer.getBalance() >= money) 
        {
            theCustomer.withdraw(money);
            updateDisplayInfo();
        }
    }
    
    @FXML private void HandleBuy100() 
    {
        int money = 100 + theCustomer.getLevelFee();
        if (theCustomer.getBalance() >= money) 
        {
            theCustomer.withdraw(money);
            updateDisplayInfo();
        }
    }
    
    @FXML private void HandleBuy200() 
    {
        int money = 200 + theCustomer.getLevelFee();
        if (theCustomer.getBalance() >= money) 
        {
            theCustomer.withdraw(money);
            updateDisplayInfo();
        }
    }
    
    @FXML private void HandleDeposit()
    {
        if (checkValidInput()) {
            double money = Double.parseDouble(txtMoneyChange.getText());
            if (money > 0) {
                theCustomer.deposit(money);
                updateDisplayInfo();
            }
        }
    }
    
    @FXML private void HandleWithdraw()
    {
        if (checkValidInput()) {
            double money = Double.parseDouble(txtMoneyChange.getText());
            if (money > 0 && money <= theCustomer.getBalance()) {
                theCustomer.withdraw(money);
                updateDisplayInfo();
            }
        }
    }
    
    private void updateDisplayInfo() 
    {
        lblBalance.setText("  Balance: $" + String.format("%.2f",theCustomer.getBalance()));
        lblLevel.setText("  Level: " + theCustomer.getLevelName());
        lblPurchasePaintings.setText("Purchase our paintings! (Fee = $" + theCustomer.getLevelFee() + ")" );
    }
    
    private boolean checkValidInput() 
    {
        try 
        {
            double money = Double.parseDouble(txtMoneyChange.getText());
            return true;
        }
        catch(Exception e) 
        {
            System.out.println("Invalid input for amount entered");
            return false;
        }
    }
    
}
