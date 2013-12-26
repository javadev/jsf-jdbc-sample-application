/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QBank.DAL.DataModel;

/**
 *
 * @author GilC
 */
public class Question {
    private String Question = "";
    private String Answer = "";
    private String Attachment = "";
    
    public Question(String strQuestion, String strAnswer, String strAttachment)
    {
        Question = strQuestion;
        Answer = strAnswer;
        Attachment = strAttachment;
    }
    
    /**
     * @return the Question
     */
    public String getQuestion() {
        return Question;
    }

    /**
     * @param Question the Question to set
     */
    public void setQuestion(String Question) {
        this.Question = Question;
    }

    /**
     * @return the Answer
     */
    public String getAnswer() {
        return Answer;
    }

    /**
     * @param Answer the Answer to set
     */
    public void setAnswer(String Answer) {
        this.Answer = Answer;
    }

    /**
     * @return the Attachment
     */
    public String getAttachment() {
        return Attachment;
    }

    /**
     * @param Attachment the Attachment to set
     */
    public void setAttachment(String Attachment) {
        this.Attachment = Attachment;
    }
}
