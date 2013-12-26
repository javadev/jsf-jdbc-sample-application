/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QBank.BL;
import java.io.Serializable;
import java.util.Random;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import QBank.DAL.DataModel.*;
import QBank.DAL.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.faces.context.FacesContext;

@Named
@SessionScoped
public class SearcherBean implements Serializable {
    private String strSearch = "";
    private List<SiteSearch> arrResults = null;
    
    public SearcherBean() {}
        
    public String getUserSearch() 
    {
        return strSearch;
    }
        
    public void setUserSearch(String user_search)
        throws SQLException 
    {
        strSearch = user_search;
        setResults(AccessMethods.SearchSite(strSearch));
    }          

    /**
     * @return the arrResults
     */
    public List<SiteSearch> getResults() {
        return arrResults;
    }

    /**
     * @param arrResults the arrResults to set
     */
    private void setResults(List<SiteSearch> arrResults) {
        this.arrResults = arrResults;
    }
    
    public void redirectToSearch() throws IOException
    {
        FacesContext.getCurrentInstance().getExternalContext().redirect("./searchresults.xhtml?search=" + this.getUserSearch());
    }
    
    public void initSearch()
        throws SQLException 
    {
        String query = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("search");
        if (query != null && !query.equals(""))
        {
            setUserSearch(query);
        }        
    }
}
