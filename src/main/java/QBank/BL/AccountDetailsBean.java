/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QBank.BL;

import QBank.DAL.AccessMethods;
import QBank.DAL.DataModel.*;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Random;

/**
 *
 * @author GilC
 */
@Named
@SessionScoped
public class AccountDetailsBean implements Serializable 
{
    final int NUM_OF_DAYS = 7;
    final int MAX_TRANS_COUNT = 3;
    final int MAX_TRANS_AMOUNT = 2000;
    final int MIN_TRANS_AMOUNT = 100;
    ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();

    private String AccountNum;
    private String Owner;
    private AccountData CurrentAccountData;

    public void initAccountData()
            throws IOException, SQLException
    {            
        int intAccountNum = 0;
        String query = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("accountnum");
        try
        {
            intAccountNum = Integer.parseInt(query);
        }
        catch (Exception ex)
        {
            FacesContext.getCurrentInstance().getExternalContext().redirect("./index.xhtml");
        }

        Account accountDetails = AccessMethods.GetAccountDetails(query);
        if (accountDetails == null)
        {
            FacesContext.getCurrentInstance().getExternalContext().redirect("./index.xhtml");
        }
        
        setCurrentAccountData(GetFakeAccountData(intAccountNum));
        setAccountNum(Integer.toString(intAccountNum));
        setOwner(accountDetails.getName());


        /*
        foreach (GridViewRow curr in grvData.Rows)
        {
            if (int.Parse(curr.Cells[3].Text) > 0)
            {
                curr.Cells[3].CssClass = "PositiveBalance";
            }
            else
            {
                curr.Cells[3].CssClass = "NegativeBalance";
            }
        }*/
    }

    /**
     * @return the CurrentAccountData
     */
    public AccountData getCurrentAccountData() {
        return CurrentAccountData;
    }

    /**
     * @param CurrentAccountData the CurrentAccountData to set
     */
    private void setCurrentAccountData(AccountData CurrentAccountData) {
        this.CurrentAccountData = CurrentAccountData;
    }
        

    /**
     * @return the NumOfDays
     */
    public int getNumOfDays() {
        return NUM_OF_DAYS;
    }

    /**
     * @return the AccountNum
     */
    public String getAccountNum() {
        return AccountNum;
    }

    /**
     * @param AccountNum the AccountNum to set
     */
    public void setAccountNum(String AccountNum) {
        this.AccountNum = AccountNum;
    }

    /**
     * @return the Owner
     */
    public String getOwner() {
        return Owner;
    }

    /**
     * @param Owner the Owner to set
     */
    public void setOwner(String Owner) {
        this.Owner = Owner;
    }
       
    public void setContextVariable(String Name, Object Value)
    {
        servletContext.setAttribute(Name, Value);
    }
    
    public Object getContextVariable(String Name)
    {
        return servletContext.getAttribute(Name);
    }
    
    protected AccountData GetFakeAccountData(int intAccountNum)
    {            
        if (getContextVariable("Accounts") == null)
        { 
            setContextVariable("Accounts", new ArrayList<AccountData>());
        }

        for (AccountData curr : (List<AccountData>)getContextVariable("Accounts"))
        {
            if (curr.getAccountNum() == intAccountNum)
            {
                return curr;
            }
        }

        List<AccountData> temp = (List<AccountData>)getContextVariable("Accounts");
        AccountData newcurr = new AccountData();
        newcurr.setAccountNum(intAccountNum);
        newcurr.setData(GenerateFakeAccountData(intAccountNum, NUM_OF_DAYS));
        temp.add(newcurr);
        setContextVariable("Accounts", temp);
        return newcurr;
    }

    private String CurrentDateAddDays(int daysToAdd)
    {
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            c.setTime(sdf.parse(new Date().toString()));
            c.add(Calendar.DATE, daysToAdd);  // number of days to add
            return sdf.format(c.getTime());
        }
        catch (ParseException ex)
        {
            return new Date().toString();
        }
    }
    private ArrayList<AccountDataRow> GenerateFakeAccountData(int intAccountNum, int intNumOfDays)
    {
        Random rand = new Random();
        ArrayList<AccountStatement> tabAccountStatementDemo = new ArrayList<>();        
        tabAccountStatementDemo.add(new AccountStatement("INSTITUTION CREDIT", 1));
        tabAccountStatementDemo.add(new AccountStatement("SAVE A-C CHARGE", -1));
        tabAccountStatementDemo.add(new AccountStatement("STANDING ORDER", -1));
        tabAccountStatementDemo.add(new AccountStatement("CREDITING OF BANKS", -1));
        tabAccountStatementDemo.add(new AccountStatement("MASTERCARD CORPORATE", -1));
        tabAccountStatementDemo.add(new AccountStatement("INTERNATIONAL VISA", -1));
        tabAccountStatementDemo.add(new AccountStatement("ATM WITHDRAW", -1));
        tabAccountStatementDemo.add(new AccountStatement("ATM WITHDRAW", -1));
        tabAccountStatementDemo.add(new AccountStatement("ATM WITHDRAW", -1));
        tabAccountStatementDemo.add(new AccountStatement("ATM WITHDRAW", -1));
        tabAccountStatementDemo.add(new AccountStatement("LOANS", -1));
        tabAccountStatementDemo.add(new AccountStatement("SALARY", 1));
        tabAccountStatementDemo.add(new AccountStatement("STOCK REVENIEWS", 1));
        tabAccountStatementDemo.add(new AccountStatement("CASH DEPOSIT", 1));
        tabAccountStatementDemo.add(new AccountStatement("CASH DEPOSIT", 1));
        tabAccountStatementDemo.add(new AccountStatement("CASH DEPOSIT", 1));
        tabAccountStatementDemo.add(new AccountStatement("DIRECT DEBIT", -1));
        tabAccountStatementDemo.add(new AccountStatement("CHECK DEPOSIT", 1));
        tabAccountStatementDemo.add(new AccountStatement("CHECK WITHDRAW", -1));
        tabAccountStatementDemo.add(new AccountStatement("MORTAGE PAYMENT", -1));

        ArrayList<AccountDataRow> ret = new ArrayList<>();
        int intBalance = rand.nextInt(10000) - 5000;
        int intTransNum = rand.nextInt(100000);

        for (int i = 0; i < intNumOfDays; i++)
        { 
            int intTransCount = rand.nextInt(MAX_TRANS_COUNT);
            for (int j = 0; j < intTransCount; j++)
            {
                int k = rand.nextInt(tabAccountStatementDemo.size() - 1);
                int creditdebit = tabAccountStatementDemo.get(k).getFactor() * (rand.nextInt(MAX_TRANS_AMOUNT - MIN_TRANS_AMOUNT) + MIN_TRANS_AMOUNT);
                intBalance = intBalance + creditdebit;
                ret.add(new AccountDataRow(CurrentDateAddDays(intNumOfDays * (-1) + i), 
                    tabAccountStatementDemo.get(k).getDescription(),
                    creditdebit,
                    intBalance,
                    Integer.toString(intTransNum++)));
            }
        }

        return ret;
    }
}