import javax.swing.*;
import java.awt.*;

public class Math_quiz implements Quiz_Subject {
    private Question[] questions;
    
    public Math_quiz() {
        // Initialize math questions - using Unicode for special characters
        questions = new Question[] {
            new Question("What is 5 \u00D7 7?",  // × symbol
                new String[]{"35", "12", "42", "30"}, 0),
            
            new Question("Solve: 2x + 5 = 15", 
                new String[]{"x = 5", "x = 10", "x = 7.5", "x = 5.5"}, 0),
            
            new Question("What is the square root of 144?", 
                new String[]{"12", "14", "16", "11"}, 0),
            
            new Question("What is 3/4 as a percentage?", 
                new String[]{"75%", "25%", "50%", "100%"}, 0),
            
            new Question("What is the area of a circle with radius 7? (\u03C0 \u2248 3.14)",  // π ≈
                new String[]{"153.86", "43.96", "21.98", "49"}, 0),
            
            new Question("Simplify: (8 + 3) \u00D7 4",  // ×
                new String[]{"44", "35", "32", "56"}, 0),
            
            new Question("What is 15% of 200?", 
                new String[]{"30", "15", "45", "20"}, 0),
            
            new Question("Solve: x\u00B2 = 64",  // ²
                new String[]{"x = 8", "x = 32", "x = 16", "x = 4"}, 0),
            
            new Question("What is the next prime number after 7?", 
                new String[]{"11", "9", "13", "17"}, 0),
            
            new Question("Convert 0.75 to a fraction:", 
                new String[]{"3/4", "1/4", "2/3", "4/5"}, 0),
            
            new Question("What is 12\u00B2?",  // ²
                new String[]{"144", "121", "169", "100"}, 0),
            
            new Question("Solve: 3y - 7 = 20", 
                new String[]{"y = 9", "y = 10", "y = 8", "y = 7"}, 0),
            
            new Question("What is 1/3 + 1/6?", 
                new String[]{"1/2", "2/9", "2/3", "1/9"}, 0),
            
            new Question("Calculate: 7! (factorial)", 
                new String[]{"5040", "720", "40320", "120"}, 0),
            
            new Question("What is the value of \u03C0 to two decimal places?",  // π
                new String[]{"3.14", "3.16", "3.12", "3.18"}, 0),
            
            new Question("Solve: 5\u00B2 + 12\u00B2 = ?",  // ²
                new String[]{"169", "144", "196", "121"}, 0),
            
            new Question("What is 0.125 as a fraction?", 
                new String[]{"1/8", "1/4", "1/16", "1/32"}, 0),
            
            new Question("Find the perimeter of a square with side 6:", 
                new String[]{"24", "36", "12", "18"}, 0),
            
            new Question("What is 2\u00B3 \u00D7 3\u00B2?",  // ³ × ²
                new String[]{"72", "36", "54", "108"}, 0),
            
            new Question("Solve: log\u2081\u2080100 = ?",  // 
                new String[]{"2", "10", "1", "100"}, 0)
        };
    }
    
    @Override
    public String getSubjectName() {
        return "MATHEMATICS";
    }
    
    @Override
    public Question[] getQuestions() {
        return questions;
    }
    
    @Override
    public void startQuiz(JFrame parentFrame, int userId) {
        // Create and show the quiz dialog with userId
        Quiz_dialoge dialog = new Quiz_dialoge(this, parentFrame, userId);
        dialog.setVisible(true);
    }
}