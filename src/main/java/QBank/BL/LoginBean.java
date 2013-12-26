/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QBank.BL;
import java.io.Serializable;
import java.util.Random;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import QBank.DAL.AccessMethods;
import QBank.DAL.DataModel.*;
import java.sql.SQLException;

/**
 *
 * @author GilC
 */
@Named
@SessionScoped
public class LoginBean implements Serializable {
    private String strUserID = "";
    private String strUsername = "";
    private String strPassword = "";   
    private String strFriendlyName = "Visitor";
    private String strError = "";
    
    public LoginBean() {}
    
    private void setUserID(String user_id) {
        strUserID = user_id;
    }

    public String getUserID() {
        return strUserID;
    }
    
    private void setError(String error) {
        strError = error;
    }

    public String getError() {
        String ret = strError;
        strError = "";
        return ret;
    }
    
    public void setUsername(String user_name) {
        strUsername = user_name;
    }

    public String getUsername() {
        return strUsername;
    }
    
    public void setPassword(String user_password) 
        throws SQLException {
        strPassword = user_password;      
        Login();
    }

    public String getPassword() {
        return strPassword;
    }
    
    public String getFriendlyName()            
    {
        return strFriendlyName;
    }
    
    private void setFriendlyName(String friendly_name)            
    {
        strFriendlyName = friendly_name;
    }
    
    private void Login()
            throws SQLException 
    {
        Account curr = AccessMethods.Login(strUsername, strPassword);
        if (curr != null)
        {
            setFriendlyName(curr.getName());
            setUserID(curr.getAccountNumber());
        }       
        else
        {
            setError("Invalid account number or password");
        }            
    }
    
    public void doLogout()
    {
        setFriendlyName("Visitor");
        setUserID("");
    }
}
