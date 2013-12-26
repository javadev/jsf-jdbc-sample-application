/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QBank.DAL.DataModel;

/**
 *
 * @author GilC
 */
public final class AccountDataRow {
    private String Date;
    private String Description;
    private int CreditDebit;
    private int Balance;
    private String TransactionNumber;

    public AccountDataRow()
    {
    }
    
    public AccountDataRow(String date, String description, int creditDebit, int balance, String transactionNumber)
    {
        setDate(date);
        setDescription(description);
        setCreditDebit(creditDebit);
        setBalance(balance);
        setTransactionNumber(transactionNumber);
    }
    /**
     * @return the Date
     */
    public String getDate() {
        return Date;
    }

    /**
     * @param Date the Date to set
     */
    public void setDate(String Date) {
        this.Date = Date;
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
     * @return the CreditDebit
     */
    public int getCreditDebit() {
        return CreditDebit;
    }

    /**
     * @param CreditDebit the CreditDebit to set
     */
    public void setCreditDebit(int CreditDebit) {
        this.CreditDebit = CreditDebit;
    }

    /**
     * @return the Balance
     */
    public int getBalance() {
        return Balance;
    }

    /**
     * @param Balance the Balance to set
     */
    public void setBalance(int Balance) {
        this.Balance = Balance;
    }

    /**
     * @return the TransactionNumber
     */
    public String getTransactionNumber() {
        return TransactionNumber;
    }

    /**
     * @param TransactionNumber the TransactionNumber to set
     */
    public void setTransactionNumber(String TransactionNumber) {
        this.TransactionNumber = TransactionNumber;
    }   
}
