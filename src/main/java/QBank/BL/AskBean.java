/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QBank.BL;

import QBank.DAL.DataModel.Question;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import QBank.DAL.AccessMethods;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.SQLException;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 *
 * @author GilC
 */
@Named
@SessionScoped
public class AskBean implements Serializable {
    private String NewQuestion;
    private Part NewQuestionFile;
    private String Error;
    
    public List<Question> getQuestions()
        throws SQLException
    {
        return AccessMethods.GetQuestions();
    }

    /**
     * @return the NewQuestion
     */
    public String getNewQuestion() {
        return NewQuestion;
    }

    /**
     * @param NewQuestion the NewQuestion to set
     */
    public void setNewQuestion(String NewQuestion) {
        this.NewQuestion = NewQuestion;
    }

    /**
     * @return the NewQuestionFile
     */
    public Part getNewQuestionFile() {
        return NewQuestionFile;
    }

    /**
     * @param NewQuestionFile the NewQuestionFile to set
     */
    public void setNewQuestionFile(Part NewQuestionFile) {
        this.NewQuestionFile = NewQuestionFile;
    }

    /**
     * @return the Error
     */
    public String getError() {
        return Error;
    }

    /**
     * @param Error the Error to set
     */
    public void setError(String Error) {
        this.Error = Error;
    }
    
    public void doAddQuestion()
    {
        if ("".equals(NewQuestion) && NewQuestion == null)
        {
            setError("Please enter a question");
        }       
        else
        {
            String strUploadedFilePath = null;
            if (NewQuestionFile != null)
            {
                try                
                {
                    String strFileName = getFilename(NewQuestionFile);
                    if (!"".equals(strFileName))
                    {
                        String strExtension = "";
                        int i = strFileName.lastIndexOf('.');
                        if (i > 0) {
                            strExtension = strFileName.substring(i+1);
                        }

                        String strFilePath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/attachments/" + strFileName);

                        if (!(strExtension == "jpg" || strExtension == "jpeg" || 
                            strExtension == "pdf" || strExtension == "bmp") &&
                            !NewQuestionFile.getContentType().contains("image"))
                        {
                            setError("Invalid File Type! (Pictures and PDF only)");
                            return;
                        }
                        else
                        {       
                            FileOutputStream file = new FileOutputStream(strFilePath);
                            InputStream uploaded = NewQuestionFile.getInputStream();
                            byte[] buffer = new byte[1024];
                            int len = uploaded.read(buffer);
                            while (len != -1) {
                                file.write(buffer, 0, len);
                                len = uploaded.read(buffer);
                            }     
                            file.flush();
                            strUploadedFilePath = "/attachments/" + strFileName;
                        }
                    }
                }
                catch (Exception ex)
                {
                    setError(ex.getMessage());
                    return;
                }
            }            
             
            try
            {
                AccessMethods.AddQuestions(NewQuestion, strUploadedFilePath);
                NewQuestion = "";
                setError("");
                strUploadedFilePath = null;
            }
            catch (Exception ex)
            {
                setError(ex.getMessage());
            }
        }
    }
    
    private static String getFilename(Part part) {  
        for (String cd : part.getHeader("content-disposition").split(";")) {  
            if (cd.trim().startsWith("filename")) {  
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");                
            }  
        }  
        return null;  
    }
}
