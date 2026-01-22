import javax.swing.*;
import java.awt.*;

public class Computer_Quiz implements Quiz_Subject {
    private Question[][] questionsByDifficulty;
    
    public Computer_Quiz() {
        questionsByDifficulty = new Question[3][];
        
        questionsByDifficulty[0] = new Question[] {
            new Question("What does CPU stand for?",
                new String[]{"Central Processing Unit", "Computer Processing Unit", "Central Process Unit", "Computer Personal Unit"}, 
                0, 1),
            
            new Question("Which of these is an operating system?",
                new String[]{"Microsoft Word", "Windows", "Google Chrome", "Photoshop"}, 
                1, 1),
            
            new Question("What does RAM stand for?",
                new String[]{"Random Access Memory", "Read Access Memory", "Random Available Memory", "Read Available Memory"}, 
                0, 1),
            
            new Question("Which company makes Mac computers?",
                new String[]{"Microsoft", "Apple", "IBM", "Dell"}, 
                1, 1),
            
            new Question("What is the main purpose of a mouse?",
                new String[]{"To type letters", "To point and click", "To print documents", "To store files"}, 
                1, 1),
            
            new Question("Which is a web browser?",
                new String[]{"Windows", "Firefox", "Excel", "Java"}, 
                1, 1),
            
            new Question("What does USB stand for?",
                new String[]{"Universal Serial Bus", "United System Bus", "Universal System Board", "United Serial Board"}, 
                0, 1),
            
            new Question("Which key is used to type capital letters?",
                new String[]{"Ctrl", "Alt", "Shift", "Enter"}, 
                2, 1),
            
            new Question("What is software?",
                new String[]{"Computer hardware parts", "Programs and applications", "Computer cables", "The computer case"}, 
                1, 1),
            
            new Question("Which stores data permanently?",
                new String[]{"RAM", "Hard Drive", "CPU", "Monitor"}, 
                1, 1),
            
            new Question("What is the brain of the computer?",
                new String[]{"Monitor", "CPU", "Keyboard", "RAM"}, 
                1, 1),
            
            new Question("How many bytes in a kilobyte?",
                new String[]{"100", "1000", "1024", "1048"}, 
                2, 1),
            
            new Question("Which is an input device?",
                new String[]{"Printer", "Monitor", "Speaker", "Keyboard"}, 
                3, 1),
            
            new Question("What does WWW stand for?",
                new String[]{"World Wide Web", "World Web Wide", "Wide World Web", "Web World Wide"}, 
                0, 1),
            
            new Question("Which is a social media platform?",
                new String[]{"Excel", "Facebook", "Windows", "Java"}, 
                1, 1),
            
            new Question("What does HTML stand for?",
                new String[]{"HyperText Markup Language", "HighText Machine Language", "HyperTool Markup Language", "HighTool Machine Language"}, 
                0, 1),
            
            new Question("Which is a programming language?",
                new String[]{"Windows", "Java", "Excel", "Photoshop"}, 
                1, 1),
            
            new Question("What is a computer virus?",
                new String[]{"A hardware problem", "A harmful program", "A type of cable", "A slow internet"}, 
                1, 1),
            
            new Question("Which key deletes text to the right?",
                new String[]{"Backspace", "Delete", "Insert", "Home"}, 
                1, 1),
            
            new Question("What does PDF stand for?",
                new String[]{"Portable Document Format", "Printed Document File", "Personal Data File", "Public Document Format"}, 
                0, 1)
        };
        
        questionsByDifficulty[1] = new Question[] {
            new Question("Which data structure uses LIFO principle?",
                new String[]{"Queue", "Stack", "Array", "Linked List"}, 
                1, 2),
            
            new Question("What is the time complexity of binary search?",
                new String[]{"O(n)", "O(log n)", "O(n^2)", "O(1)"}, 
                1, 2),
            
            new Question("Which is NOT an OOP principle?",
                new String[]{"Encapsulation", "Inheritance", "Compilation", "Polymorphism"}, 
                2, 2),
            
            new Question("What does SQL stand for?",
                new String[]{"Structured Query Language", "Simple Question Language", "System Query Logic", "Structured Question Logic"}, 
                0, 2),
            
            new Question("Which protocol is used for email?",
                new String[]{"FTP", "HTTP", "SMTP", "TCP"}, 
                2, 2),
            
            new Question("What is a primary key in database?",
                new String[]{"A foreign key reference", "A unique identifier for each record", "The main password", "The first column"}, 
                1, 2),
            
            new Question("Which is a compiled language?",
                new String[]{"Python", "JavaScript", "C++", "PHP"}, 
                2, 2),
            
            new Question("What does API stand for?",
                new String[]{"Application Programming Interface", "Advanced Programming Interface", "Application Process Integration", "Advanced Process Integration"}, 
                0, 2),
            
            new Question("Which is NOT a JavaScript framework?",
                new String[]{"React", "Angular", "Django", "Vue"}, 
                2, 2),
            
            new Question("What is polymorphism in OOP?",
                new String[]{"Hiding implementation details", "Reusing code from parent class", "One interface, multiple implementations", "Bundling data and methods"}, 
                2, 2),
            
            new Question("What is the default port for HTTPS?",
                new String[]{"80", "443", "8080", "21"}, 
                1, 2),
            
            new Question("Which algorithm is used for shortest path?",
                new String[]{"Bubble Sort", "Dijkstra", "Quick Sort", "Binary Search"}, 
                1, 2),
            
            new Question("What is a constructor?",
                new String[]{"A method that destroys objects", "A special method to initialize objects", "A type of variable", "An error handler"}, 
                1, 2),
            
            new Question("Which is a NoSQL database?",
                new String[]{"MySQL", "MongoDB", "PostgreSQL", "Oracle"}, 
                1, 2),
            
            new Question("What is version control?",
                new String[]{"Managing software versions", "Controlling computer speed", "Version of operating system", "Hardware version management"}, 
                0, 2),
            
            new Question("Which is a Linux distribution?",
                new String[]{"Windows", "Ubuntu", "macOS", "iOS"}, 
                1, 2),
            
            new Question("What does CSS stand for?",
                new String[]{"Cascading Style Sheets", "Computer Style Sheets", "Creative Style System", "Cascading System Styles"}, 
                0, 2),
            
            new Question("What is recursion?",
                new String[]{"A loop structure", "A function calling itself", "A data type", "An error type"}, 
                1, 2),
            
            new Question("Which is a design pattern?",
                new String[]{"Singleton", "Compiler", "Interpreter", "Debugger"}, 
                0, 2),
            
            new Question("What does DNS stand for?",
                new String[]{"Domain Name System", "Data Name Service", "Domain Network System", "Data Network Service"}, 
                0, 2)
        };
        
        questionsByDifficulty[2] = new Question[] {
            new Question("What is the time complexity of merge sort?",
                new String[]{"O(n log n)", "O(n^2)", "O(log n)", "O(n)"}, 
                0, 3),
            
            new Question("Which is a functional programming language?",
                new String[]{"Java", "Haskell", "C++", "Python"}, 
                1, 3),
            
            new Question("What is memoization?",
                new String[]{"A sorting technique", "Storing results of expensive calls", "A type of loop", "Memory allocation"}, 
                1, 3),
            
            new Question("Which algorithm prevents deadlock?",
                new String[]{"Bankers Algorithm", "Quick Sort", "Dijkstra", "Binary Search"}, 
                0, 3),
            
            new Question("What is a monad in functional programming?",
                new String[]{"A design pattern", "A programming structure for computations", "A type of loop", "An error handler"}, 
                1, 3),
            
            new Question("Which is a CAP theorem tradeoff?",
                new String[]{"Speed vs Memory", "Consistency vs Availability", "Cost vs Performance", "Security vs Usability"}, 
                1, 3),
            
            new Question("What is tail recursion optimization?",
                new String[]{"Optimizing loop structures", "Reusing stack frames for recursive calls", "Sorting algorithm optimization", "Memory optimization"}, 
                1, 3),
            
            new Question("Which is a consensus algorithm?",
                new String[]{"Raft", "Quick Sort", "Merge Sort", "Binary Search"}, 
                0, 3),
            
            new Question("What is the ChurchTuring thesis?",
                new String[]{"About Church architecture", "All computable functions are Turing computable", "A programming paradigm", "A hardware design principle"}, 
                1, 3),
            
            new Question("Which is a side effect in functional programming?",
                new String[]{"Pure function execution", "Modifying external state", "Returning a value", "Taking parameters"}, 
                1, 3),
            
            new Question("What is the halting problem?",
                new String[]{"A sorting problem", "Determining if a program will finish running", "A memory allocation issue", "A network problem"}, 
                1, 3),
            
            new Question("Which is a P vs NP problem?",
                new String[]{"A solved problem", "Whether P equals NP", "A hardware design issue", "A memory management problem"}, 
                1, 3),
            
            new Question("What is referential transparency?",
                new String[]{"A type of variable", "Function can be replaced with its value", "Memory reference", "Data transparency"}, 
                1, 3),
            
            new Question("Which is a monoid in category theory?",
                new String[]{"A set with associative binary operation and identity", "A type of function", "A data structure", "An algorithm"}, 
                0, 3),
            
            new Question("What is continuationpassing style?",
                new String[]{"A programming style using continuations", "A type of loop", "A sorting algorithm", "A design pattern"}, 
                0, 3),
            
            new Question("Which is a functor in category theory?",
                new String[]{"A type of variable", "A mapping between categories", "A function type", "A data structure"}, 
                1, 3),
            
            new Question("What is the actor model?",
                new String[]{"A database model", "A concurrent computation model", "A sorting algorithm", "A network protocol"}, 
                1, 3),
            
            new Question("Which is a linear type system?",
                new String[]{"A type system tracking resource usage", "A simple type system", "A dynamic type system", "A weak type system"}, 
                0, 3),
            
            new Question("What is a zipper data structure?",
                new String[]{"A tree traversal structure", "A sorting algorithm", "A network protocol", "A database index"}, 
                0, 3),
            
            new Question("Which is a dependent type system?",
                new String[]{"Types depending on values", "Simple type annotations", "Dynamic typing", "Weak typing"}, 
                0, 3)
        };
    }
    
    @Override
    public String getSubjectName() {
        return "COMPUTER";
    }
    
    @Override
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