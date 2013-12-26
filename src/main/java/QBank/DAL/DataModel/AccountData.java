/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QBank.DAL.DataModel;

import java.util.List;

/**
 *
 * @author GilC
 */
public class AccountData 
{
    private int AccountNum;
    private List<AccountDataRow> Data;

    /**
     * @return the AccountNum
     */
    public int getAccountNum() {
        return AccountNum;
    }

    /**
     * @param AccountNum the AccountNum to set
     */
    public void setAccountNum(int AccountNum) {
        this.AccountNum = AccountNum;
    }

    /**
     * @return the Data
     */
    public List<AccountDataRow> getData() {
        return Data;
    }

    /**
     * @param Data the Data to set
     */
    public void setData(List<AccountDataRow> Data) {
        this.Data = Data;
    }        
}
