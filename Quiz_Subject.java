import javax.swing.*;

public interface Quiz_Subject {
    String getSubjectName();
    Question[] getQuestions();
    void startQuiz(JFrame parentFrame);
}