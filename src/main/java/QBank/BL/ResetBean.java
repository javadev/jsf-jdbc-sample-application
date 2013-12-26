/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QBank.BL;
import QBank.DAL.AccessMethods;
import QBank.DAL.DataModel.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author GilC
 */
@Named
@SessionScoped
public class ResetBean implements Serializable{
    public void doReset()
            throws SQLException, IOException
    {
        AccessMethods.ResetDB();

        File index1 = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/index.htm"));
        
        if (index1.exists())
        {
            index1.delete();
        }
        
        File index2 = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/index.html"));
        
        if (index2.exists())
        {
            index2.delete();
        }

        File attachmentDir = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/attachments"));
        
        for (File curr : attachmentDir.listFiles())
        {
            if (!curr.getName().contains("1.jpg"))
            {
                curr.delete();
            }
        }

        FacesContext.getCurrentInstance().getExternalContext().redirect("./index.xhtml");
    }
}
