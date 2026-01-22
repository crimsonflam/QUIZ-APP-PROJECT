import javax.swing.*;
import java.awt.*;

public class Math_quiz implements Quiz_Subject {
    private Question[][] questionsByDifficulty; // [difficulty][questions]
    
    public Math_quiz() {
        // Initialize 3 difficulty levels
        questionsByDifficulty = new Question[3][];
        
        // ========== EASY QUESTIONS (Level 1) ==========
        questionsByDifficulty[0] = new Question[] {
            // Basic arithmetic
            new Question("What is 5 + 7?", 
                new String[]{"12", "35", "42", "30"}, 0, 1),
            new Question("What is 15 - 8?", 
                new String[]{"7", "23", "8", "9"}, 0, 1),
            new Question("What is 6 \u00D7 9?",  
                new String[]{"54", "63", "45", "36"}, 0, 1),
            new Question("What is 48 \u00F7 6?", 
                new String[]{"8", "6", "7", "9"}, 0, 1),
            new Question("What is 3/4 as a decimal?", 
                new String[]{"0.75", "0.25", "0.5", "0.3"}, 0, 1),
            
            // Simple algebra
            new Question("Solve: x + 5 = 12", 
                new String[]{"x = 7", "x = 17", "x = 6", "x = 8"}, 0, 1),
            new Question("Solve: 2y = 18", 
                new String[]{"y = 9", "y = 16", "y = 20", "y = 36"}, 0, 1),
            
            // Geometry basics
            new Question("What is the area of a rectangle 5\u00D74?",  
                new String[]{"20", "18", "9", "25"}, 0, 1),
            new Question("Perimeter of square side 3?", 
                new String[]{"12", "9", "6", "15"}, 0, 1),
            new Question("How many degrees in a right angle?", 
                new String[]{"90\u00B0", "180\u00B0", "45\u00B0", "360\u00B0"}, 0, 1), 
            
            // Fractions
            new Question("What is 1/2 + 1/4?", 
                new String[]{"3/4", "1/6", "2/6", "1/2"}, 0, 1),
            new Question("Convert 0.5 to fraction:", 
                new String[]{"1/2", "1/4", "1/5", "5/10"}, 0, 1),
            
            // Percentages
            new Question("What is 10% of 200?", 
                new String[]{"20", "10", "30", "15"}, 0, 1),
            new Question("50% as fraction:", 
                new String[]{"1/2", "1/4", "1/5", "5/10"}, 0, 1),
            
            // More basic math
            new Question("What is 7\u00B2?",  
                new String[]{"49", "14", "21", "28"}, 0, 1),
            new Question("Square root of 64?", 
                new String[]{"8", "6", "7", "9"}, 0, 1),
            new Question("Next number: 2, 4, 6, 8, ?", 
                new String[]{"10", "9", "12", "7"}, 0, 1),
            new Question("Prime number?", 
                new String[]{"7", "9", "15", "21"}, 0, 1),
            new Question("Even number?", 
                new String[]{"14", "15", "21", "33"}, 0, 1),
            new Question("Multiples of 3?", 
                new String[]{"12", "14", "16", "20"}, 0, 1)
        };
        
        // ========== MEDIUM QUESTIONS (Level 2) ==========
        questionsByDifficulty[1] = new Question[] {
            // Algebra
            new Question("Solve: 3x + 7 = 22", 
                new String[]{"x = 5", "x = 15", "x = 7", "x = 6"}, 0, 2),
            new Question("Solve: 2(x - 3) = 10", 
                new String[]{"x = 8", "x = 13", "x = 7", "x = 5"}, 0, 2),
            new Question("Factor: x\u00B2 + 5x + 6",  // ²
                new String[]{"(x+2)(x+3)", "(x+1)(x+6)", "(x-2)(x-3)", "(x+5)(x+1)"}, 0, 2),
            
            // Geometry
            new Question("Area of circle radius 5? (\u03C0\u22483.14)",  
                new String[]{"78.5", "31.4", "15.7", "25"}, 0, 2),
            new Question("Volume of cube side 4?", 
                new String[]{"64", "16", "48", "32"}, 0, 2),
            new Question("Pythagoras: 3\u00B2 + 4\u00B2 = ?",  
                new String[]{"5\u00B2", "7\u00B2", "12", "25"}, 0, 2), 
            
            // Fractions & decimals
            new Question("2/3 \u00D7 3/4 = ?",  
                new String[]{"1/2", "5/7", "6/12", "2/4"}, 0, 2),
            new Question("Convert 0.375 to fraction:", 
                new String[]{"3/8", "1/3", "2/5", "5/13"}, 0, 2),
            
            // Percentages
            new Question("25% of 480 = ?", 
                new String[]{"120", "100", "96", "125"}, 0, 2),
            new Question("Increase 200 by 15%:", 
                new String[]{"230", "215", "2300", "300"}, 0, 2),
            
            // Exponents
            new Question("2\u00B3 \u00D7 2\u00B2 = ?",  
                new String[]{"32", "64", "16", "12"}, 0, 2),
            new Question("5\u207B\u00B2 = ?",  
                new String[]{"1/25", "25", "0.25", "-25"}, 0, 2),
            
            // Roots
            new Question("\u221B27 = ?",  
                new String[]{"3", "9", "6", "18"}, 0, 2),
            new Question("\u221A(25 + 144) = ?",  
                new String[]{"13", "17", "12", "15"}, 0, 2),
            
            // Sequences
            new Question("Next: 1, 4, 9, 16, 25, ?", 
                new String[]{"36", "30", "49", "26"}, 0, 2),
            new Question("Fibonacci: 1, 1, 2, 3, 5, 8, ?", 
                new String[]{"13", "11", "10", "12"}, 0, 2),
            
            // Probability
            new Question("Chance of heads in coin flip:", 
                new String[]{"1/2", "1/4", "1/3", "2/3"}, 0, 2),
            new Question("Rolling 6 on a die:", 
                new String[]{"1/6", "1/3", "1/2", "5/6"}, 0, 2),
            
            // Measurements
            new Question("Convert 2.5km to meters:", 
                new String[]{"2500", "250", "2000", "25000"}, 0, 2),
            new Question("1 hour 45 min = __ minutes", 
                new String[]{"105", "145", "100", "95"}, 0, 2)
        };
        
        // ========== HARD QUESTIONS (Level 3) ==========
        questionsByDifficulty[2] = new Question[] {
            // Advanced algebra
            new Question("Solve: x\u00B2 - 5x + 6 = 0",  
                new String[]{"x=2,3", "x=1,6", "x=-2,-3", "x=0,5"}, 0, 3),
            new Question("Solve system: x+y=10, x-y=4", 
                new String[]{"x=7,y=3", "x=6,y=4", "x=8,y=2", "x=5,y=5"}, 0, 3),
            new Question("Simplify: (x\u00B3 \u00D7 x\u2074) / x\u00B2",  
                new String[]{"x\u2075", "x\u2079", "x\u2077", "x\u2076"}, 0, 3), 
            
            // Calculus
            new Question("Derivative of 3x\u00B2 + 4x - 5",  
                new String[]{"6x + 4", "3x + 4", "6x\u00B2 + 4", "x\u00B3 + 2x\u00B2 - 5x"}, 0, 3),
            new Question("\u222B(2x + 3) dx",  
                new String[]{"x\u00B2 + 3x + C", "x\u00B2 + 3", "2x\u00B2 + 3x", "x + 3x\u00B2 + C"}, 0, 3),
            new Question("Limit: lim(x\u21922) (x\u00B2-4)/(x-2)",  
                new String[]{"4", "0", "2", "\u221E"}, 0, 3), 
            
            // Trigonometry
            new Question("sin\u00B2\u03B8 + cos\u00B2\u03B8 = ?",  
                new String[]{"1", "0", "sin2\u03B8", "tan\u03B8"}, 0, 3), 
            new Question("cos(60\u00B0) = ?",   
                new String[]{"1/2", "\u221A3/2", "\u221A2/2", "1/\u221A2"}, 0, 3), 
            new Question("tan(45\u00B0) = ?",  
                new String[]{"1", "0", "\u221A3", "1/\u221A3"}, 0, 3),
            
            // Complex numbers
            new Question("i\u00B2 = ? (where i=\u221A-1)",   
                new String[]{"-1", "1", "i", "0"}, 0, 3),
            new Question("(3 + 4i) + (2 - i) = ?", 
                new String[]{"5 + 3i", "1 + 5i", "6 + 3i", "5 + 5i"}, 0, 3),
            
            // Logarithms
            new Question("log\u2082(8) = ?",  
                new String[]{"3", "2", "4", "1"}, 0, 3),
            new Question("log\u2081\u2080(1000) = ?",  
                new String[]{"3", "2", "4", "1"}, 0, 3),
            new Question("e^(ln5) = ?", 
                new String[]{"5", "e\u2075", "ln5", "1"}, 0, 3), 
            
            // Matrices
            new Question("Determinant of [[2,3],[1,4]]:", 
                new String[]{"5", "8", "11", "14"}, 0, 3),
            
            // Series
            new Question("Sum of 1 to 100:", 
                new String[]{"5050", "5000", "5100", "4950"}, 0, 3),
            new Question("Sum: 2 + 4 + 6 + ... + 20", 
                new String[]{"110", "100", "90", "120"}, 0, 3),
            
            // Advanced geometry
            new Question("Volume of sphere radius 3 (V=4/3\u03C0r\u00B3):",  
                new String[]{"36\u03C0", "27\u03C0", "18\u03C0", "12\u03C0"}, 0, 3),
            new Question("Diagonal of cube side 5:", 
                new String[]{"5\u221A3", "5\u221A2", "10", "15"}, 0, 3),
            
            // Statistics
            new Question("Mean of 4, 8, 6, 5, 7:", 
                new String[]{"6", "5", "5.5", "6.5"}, 0, 3),
            new Question("Median of 1, 3, 5, 7, 9:", 
                new String[]{"5", "4", "6", "3"}, 0, 3)
        };
    }
    
    @Override
    public String getSubjectName() {
        return "MATHEMATICS";
    }
    
    // NEW METHOD: Get questions for specific difficulty
    public Question[] getQuestions(int difficultyLevel) {
        if (difficultyLevel >= 1 && difficultyLevel <= 3) {
            return questionsByDifficulty[difficultyLevel - 1];
        }
        return questionsByDifficulty[1]; // Default to medium
    }
    
    // Keep for backward compatibility (uses default/medium)
    @Override
    public Question[] getQuestions() {
        return getQuestions(2); // Default to medium
    }
    
    @Override
    public void startQuiz(JFrame parentFrame, int userId) {
        Quiz_dialoge dialog = new Quiz_dialoge(this, parentFrame, userId, 2);
        dialog.setVisible(true);
    }
}   