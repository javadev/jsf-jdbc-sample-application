/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QBank.DAL.DataModel;
import java.util.*;

/**
 *
 * @author GilC
 */
public class CreditCard {
    private String CC = "";
    private String FirstName = "";
    private String LastName = "";
    private Date ValidTill = null;

    public CreditCard(String strFirstName, String strLastName, String strCC, Date dateValidTill)
    {
        FirstName = strFirstName;
        LastName = strLastName;
        CC = strCC;
        ValidTill = dateValidTill;
    }
    /**
     * @return the CC
     */
    public String getCC() {
        return CC;
    }

    /**
     * @param CC the CC to set
     */
    public void setCC(String CC) {
        this.CC = CC;
    }

    /**
     * @return the FirstName
     */
    public String getFirstName() {
        return FirstName;
    }

    /**
     * @param FirstName the FirstName to set
     */
    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    /**
     * @return the LastName
     */
    public String getLastName() {
        return LastName;
    }

    /**
     * @param LastName the LastName to set
     */
    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    /**
     * @return the ValidTill
     */
    public Date getValidTill() {
        return ValidTill;
    }

    /**
     * @param ValidTill the ValidTill to set
     */
    public void setValidTill(Date ValidTill) {
        this.ValidTill = ValidTill;
    }
}
