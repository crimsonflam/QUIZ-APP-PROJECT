import javax.swing.JFrame;

public class Computer_Quiz implements Quiz_Subject {
    private Question[] questions;
    
    public Computer_Quiz() {
        questions = new Question[] {
            // Hardware
            new Question("What does CPU stand for?", 
                new String[]{"Central Processing Unit", "Computer Processing Unit", 
                            "Central Process Unit", "Computer Process Unit"}, 0),
            
            new Question("Which component stores data permanently?", 
                new String[]{"Hard Drive", "RAM", "CPU", "GPU"}, 0),
            
            new Question("What does RAM stand for?", 
                new String[]{"Random Access Memory", "Read Access Memory", 
                            "Random Available Memory", "Read Available Memory"}, 0),
            
            // Software
            new Question("Which company developed Windows operating system?", 
                new String[]{"Microsoft", "Apple", "Google", "IBM"}, 0),
            
            new Question("What does GUI stand for?", 
                new String[]{"Graphical User Interface", "General User Interface", 
                            "Graphical Universal Interface", "General Universal Interface"}, 0),
            
            new Question("Which programming language is known for web development?", 
                new String[]{"JavaScript", "C++", "Java", "Python"}, 0),
            
            // Internet
            new Question("What does URL stand for?", 
                new String[]{"Uniform Resource Locator", "Universal Resource Locator", 
                            "Uniform Resource Link", "Universal Resource Link"}, 0),
            
            new Question("What does HTML stand for?", 
                new String[]{"HyperText Markup Language", "HighText Machine Language", 
                            "HyperText Machine Language", "HighText Markup Language"}, 0),
            
            new Question("Which protocol is used for secure web browsing?", 
                new String[]{"HTTPS", "HTTP", "FTP", "SMTP"}, 0),
            
            // Programming
            new Question("Which symbol is used for single-line comments in Java?", 
                new String[]{"//", "/*", "#", "--"}, 0),
            
            new Question("What is the output of: 5 + 3 * 2 in most programming languages?", 
                new String[]{"11", "16", "13", "10"}, 0),
            
            new Question("Which data type stores true/false values?", 
                new String[]{"Boolean", "Integer", "String", "Float"}, 0),
            
            // Computer History
            new Question("Who is considered the father of computers?", 
                new String[]{"Charles Babbage", "Alan Turing", "Bill Gates", "Steve Jobs"}, 0),
            
            new Question("Which was the first programmable computer?", 
                new String[]{"ENIAC", "UNIVAC", "Apple I", "IBM PC"}, 0),
            
            new Question("In what year was the first iPhone released?", 
                new String[]{"2007", "2005", "2008", "2006"}, 0),
            
            // Security
            new Question("What does VPN stand for?", 
                new String[]{"Virtual Private Network", "Virtual Public Network", 
                            "Verified Private Network", "Verified Public Network"}, 0),
            
            new Question("What is malware designed to appear as legitimate software?", 
                new String[]{"Trojan Horse", "Virus", "Worm", "Spyware"}, 0),
            
            new Question("Which authentication method uses physical characteristics?", 
                new String[]{"Biometrics", "Password", "PIN", "Security Questions"}, 0),
            
            // General Knowledge
            new Question("What does 'OS' stand for in computing?", 
                new String[]{"Operating System", "Output System", "Operation System", "Optical System"}, 0),
            
            new Question("Which company developed the Java programming language?", 
                new String[]{"Sun Microsystems", "Microsoft", "Oracle", "Google"}, 0)
        };
    }
    
    @Override
    public String getSubjectName() {
        return "COMPUTER";
    }
    
    @Override
    public Question[] getQuestions() {
        return questions;
    }
    
    @Override
    public void startQuiz(JFrame parentFrame) {
        Quiz_dialoge dialog = new Quiz_dialoge(this, parentFrame);
        dialog.setVisible(true);
    }
}