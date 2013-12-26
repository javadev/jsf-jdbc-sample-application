/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QBank.DAL.DataModel;

/**
 *
 * @author GilC
 */
public class Account {
    private String AccountNumber = "";
    private String Password = "";
    private String Name = "";

    public Account(String strAccountNumber, String strPassword, String strName)
    {
        AccountNumber = strAccountNumber;
        Password = strPassword;
        Name = strName;
    }
    
    /**
     * @return the AccountNumber
     */
    public String getAccountNumber() {
        return AccountNumber;
    }

    /**
     * @param AccountNumber the AccountNumber to set
     */
    public void setAccountNumber(String AccountNumber) {
        this.AccountNumber = AccountNumber;
    }

    /**
     * @return the Password
     */
    public String getPassword() {
        return Password;
    }

    /**
     * @param Password the Password to set
     */
    public void setPassword(String Password) {
        this.Password = Password;
    }

    /**
     * @return the Name
     */
    public String getName() {
        return Name;
    }

    /**
     * @param Name the Name to set
     */
    public void setName(String Name) {
        this.Name = Name;
    }
}
