/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QBank.DAL;
import QBank.DAL.DataModel.*;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author GilC
 */
public class AccessMethods 
{
    private static Connection GetConnection()
    {
        try 
        {
            return DriverManager.getConnection("jdbc:derby://localhost:1527/QBank", "root", "root");
        }
        catch (Exception ex) 
        {
        }
        
        return null;
    }
    
    public static Account GetAccountDetails(String strAccountNumber)
        throws SQLException 
    {        
        Connection con = GetConnection();
        ResultSet rs;        
        Statement stmt;
        Account ret = null;

        try
        {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT accountnumber, name, password FROM accounts where accountnumber = '" + strAccountNumber + "'");            
            if (rs.next())
            {
                ret = new Account(rs.getString("accountnumber"), rs.getString("password"), rs.getString("name"));            
            }
            stmt.close();
            rs.close();
        }
        catch (SQLException ex)
        {
            throw ex;
        }
        finally
        {
            con.close();            
        }
                
        return ret;
    }
    
    public static List<SiteSearch> SearchSite(String strSearchTerm)
        throws SQLException 
    {        
        Connection con = GetConnection();
        ResultSet rs;        
        List<SiteSearch> arrResults = new ArrayList<>();
        Statement stmt;

        try
        {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT title, description FROM sitesearch where Title like '%" + strSearchTerm + "%' or Description like '%" + strSearchTerm + "%'");            
            
            while (rs.next())
            {
                SiteSearch curr = new SiteSearch(rs.getString("Title"), rs.getString("Description"));
                arrResults.add(curr);
            }
            stmt.close();
            rs.close();
        }
        catch (SQLException ex)
        {
            throw ex;
        }
        finally
        {
            con.close();            
        }
                
        return arrResults;
    }
    
    public static Account Login(String strAccountNumber, String strPassword)
        throws SQLException 
    {        
        Connection con = GetConnection();
        ResultSet rs;        
        Statement stmt;
        Account ret = null;

        try
        {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT accountnumber, name, password FROM accounts where accountnumber = '"
                    + strAccountNumber + "' and password = '" + strPassword + "'");            
            if (rs.next())
            {
                ret = new Account(rs.getString("accountnumber"), rs.getString("password"), rs.getString("name"));            
            }
            stmt.close();
            rs.close();
        }
        catch (SQLException ex)
        {
            throw ex;
        }
        finally
        {
            con.close();            
        }
                
        return ret;
    }
    
    public static List<Question> GetQuestions()
        throws SQLException 
    {        
        Connection con = GetConnection();
        ResultSet rs;        
        List<Question> arrResults = new ArrayList<>();
        Statement stmt;

        try
        {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT question, answer, attachment FROM questions");            
            
            while (rs.next())
            {
                Question curr = new Question(rs.getString("question"), rs.getString("answer"), rs.getString("attachment"));
                arrResults.add(curr);
            }
            stmt.close();
            rs.close();
        }
        catch (SQLException ex)
        {
            throw ex;
        }
        finally
        {
            con.close();            
        }
                
        return arrResults;
    }
    
    public static void AddQuestions(String strNewQuestion, String strNewQuestionFileName)
        throws SQLException 
    {        
        Connection con = GetConnection();              
        Statement stmt;

        try
        {
            stmt = con.createStatement();
            stmt.executeUpdate("insert into Questions values ('" + strNewQuestion + "', null, " + (strNewQuestionFileName != "" && strNewQuestionFileName != null ? "'" + strNewQuestionFileName + "'" : "null") + ")");
            stmt.close();            
        }
        catch (SQLException ex)
        {
            throw ex;
        }
        finally
        {
            con.close();            
        }        
    }
    
    public static void ResetDB()
        throws SQLException 
    {        
        Connection con = GetConnection();              
        Statement stmt;

        try
        {
            stmt = con.createStatement();
            stmt.executeUpdate("Delete from Questions where (Attachment is not null and Attachment <> '/attachments/1.jpg') or (Question like '%<%') or (Attachment is null and Answer is null and Question <> 'How fast do you answer my questions?')");
            stmt.close();            
        }
        catch (SQLException ex)
        {
            throw ex;
        }
        finally
        {
            con.close();            
        }        
    }
}
