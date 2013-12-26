/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QBank.DAL.DataModel;

/**
 *
 * @author GilC
 */
public class AccountStatement {
    private String Description;
    private int Factor;

    public AccountStatement()
    {
    }
    
    public AccountStatement(String desc, int fac)
    {
        setDescription(desc);
        setFactor(fac);
    }
    
    /**
     * @return the Description
     */
    public String getDescription() {
        return Description;
    }

    /**
     * @param Description the Description to set
     */
    public void setDescription(String Description) {
        this.Description = Description;
    }

    /**
     * @return the Factor
     */
    public int getFactor() {
        return Factor;
    }

    /**
     * @param Factor the Factor to set
     */
    public void setFactor(int Factor) {
        this.Factor = Factor;
    }
}
