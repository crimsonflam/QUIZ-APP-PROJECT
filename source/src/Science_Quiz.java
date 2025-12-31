import javax.swing.JFrame;

public class Science_Quiz implements Quiz_Subject {
    private Question[] questions;
    
    public Science_Quiz() {
        questions = new Question[] {
            // Physics
            new Question("What is the SI unit of force?", 
                new String[]{"Newton", "Joule", "Watt", "Pascal"}, 0),
            
            new Question("What is the acceleration due to gravity on Earth (approx.)?", 
                new String[]{"9.8 m/s\u00B2", "10 m/s\u00B2", "9.6 m/s\u00B2", "9.2 m/s\u00B2"}, 0),
            
            new Question("Which law states: 'For every action, there is an equal and opposite reaction'?", 
                new String[]{"Newton's Third Law", "Newton's First Law", "Newton's Second Law", "Ohm's Law"}, 0),
            
            // Chemistry
            new Question("What is the chemical symbol for gold?", 
                new String[]{"Au", "Ag", "Fe", "Pb"}, 0),
            
            new Question("What is the pH of pure water?", 
                new String[]{"7", "0", "14", "5"}, 0),
            
            new Question("Which gas is most abundant in Earth's atmosphere?", 
                new String[]{"Nitrogen", "Oxygen", "Carbon Dioxide", "Argon"}, 0),
            
            // Biology
            new Question("What is the powerhouse of the cell?", 
                new String[]{"Mitochondria", "Nucleus", "Ribosome", "Golgi Apparatus"}, 0),
            
            new Question("Which blood type is the universal donor?", 
                new String[]{"O-", "O+", "AB+", "A-"}, 0),
            
            new Question("Photosynthesis primarily occurs in which part of a plant?", 
                new String[]{"Leaves", "Roots", "Stem", "Flowers"}, 0),
            
            new Question("How many chromosomes do humans have?", 
                new String[]{"46", "23", "48", "24"}, 0),
            
            // Mixed Science
            new Question("What is the hardest natural substance on Earth?", 
                new String[]{"Diamond", "Graphite", "Quartz", "Topaz"}, 0),
            
            new Question("Which planet is known as the Red Planet?", 
                new String[]{"Mars", "Venus", "Jupiter", "Saturn"}, 0),
            
            new Question("What does DNA stand for?", 
                new String[]{"Deoxyribonucleic Acid", "Deoxyribose Nucleic Acid", 
                            "Dinucleic Acid", "Deoxynucleic Acid"}, 0),
            
            new Question("Which element has the atomic number 1?", 
                new String[]{"Hydrogen", "Helium", "Lithium", "Oxygen"}, 0),
            
            new Question("What is the speed of light in vacuum (approx.)?", 
                new String[]{"300,000 km/s", "150,000 km/s", "450,000 km/s", "600,000 km/s"}, 0),
            
            new Question("Which organ pumps blood throughout the body?", 
                new String[]{"Heart", "Lungs", "Liver", "Kidneys"}, 0),
            
            new Question("What is the chemical formula for water?", 
                new String[]{"H\u2082O", "CO\u2082", "NaCl", "O\u2082"}, 0),
            
            new Question("Which scientist developed the theory of relativity?", 
                new String[]{"Albert Einstein", "Isaac Newton", "Marie Curie", "Stephen Hawking"}, 0),
            
            new Question("What is the process by which plants make their own food?", 
                new String[]{"Photosynthesis", "Respiration", "Transpiration", "Fermentation"}, 0),
            
            new Question("Which gas do plants absorb during photosynthesis?", 
                new String[]{"Carbon Dioxide", "Oxygen", "Nitrogen", "Hydrogen"}, 0)
        };
    }
    
    @Override
    public String getSubjectName() {
        return "SCIENCE";
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