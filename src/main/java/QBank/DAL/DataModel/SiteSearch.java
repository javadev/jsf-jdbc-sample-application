/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QBank.DAL.DataModel;

/**
 *
 * @author GilC
 */
public class SiteSearch {
    private String Title = "";
    private String Description = "";
    
    public SiteSearch(String strTitle, String strDescription)    
    {
        Title = strTitle;
        Description = strDescription;
    }

    /**
     * @return the Title
     */
    public String getTitle() {
        return Title;
    }

    /**
     * @param Title the Title to set
     */
    public void setTitle(String Title) {
        this.Title = Title;
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
    
}
