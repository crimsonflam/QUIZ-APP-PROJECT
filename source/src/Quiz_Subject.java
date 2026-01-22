import javax.swing.*;

public interface Quiz_Subject {
    String getSubjectName();
     Question[] getQuestions();
    Question[] getQuestions(int difficultyLevel);
    void startQuiz(JFrame parentFrame, int userId); 
}