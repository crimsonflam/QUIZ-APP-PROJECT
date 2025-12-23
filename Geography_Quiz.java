import javax.swing.JFrame;

public class Geography_Quiz implements Quiz_Subject {
    private Question[] questions;
    
    public Geography_Quiz() {
        questions = new Question[] {
            // Countries and Capitals
            new Question("What is the capital of Japan?", 
                new String[]{"Tokyo", "Kyoto", "Osaka", "Seoul"}, 0),
            
            new Question("Which country has the largest population?", 
                new String[]{"India", "China", "United States", "Indonesia"}, 1),
            
            new Question("What is the capital of Brazil?", 
                new String[]{"Bras\u00EDlia", "Rio de Janeiro", "S\u00E3o Paulo", "Buenos Aires"}, 0),
            
            // Continents and Oceans
            new Question("Which is the largest continent?", 
                new String[]{"Asia", "Africa", "North America", "Europe"}, 0),
            
            new Question("Which ocean is the largest?", 
                new String[]{"Pacific Ocean", "Atlantic Ocean", "Indian Ocean", "Arctic Ocean"}, 0),
            
            new Question("How many continents are there?", 
                new String[]{"7", "6", "5", "8"}, 0),
            
            // Landmarks and Features
            new Question("Which is the longest river in the world?", 
                new String[]{"Nile River", "Amazon River", "Yangtze River", "Mississippi River"}, 0),
            
            new Question("What is the highest mountain in the world?", 
                new String[]{"Mount Everest", "K2", "Kangchenjunga", "Lhotse"}, 0),
            
            new Question("Which desert is the largest in the world?", 
                new String[]{"Sahara Desert", "Arabian Desert", "Gobi Desert", "Kalahari Desert"}, 0),
            
            // Cities and Locations
            new Question("In which country would you find the Great Barrier Reef?", 
                new String[]{"Australia", "Brazil", "Philippines", "Mexico"}, 0),
            
            new Question("Which city is known as the 'Big Apple'?", 
                new String[]{"New York City", "Chicago", "Los Angeles", "Las Vegas"}, 0),
            
            new Question("Where are the pyramids of Giza located?", 
                new String[]{"Egypt", "Mexico", "Sudan", "Peru"}, 0),
            
            // Geography Facts
            new Question("Which country is both a continent and a country?", 
                new String[]{"Australia", "Russia", "United States", "Canada"}, 0),
            
            new Question("What is the smallest country in the world?", 
                new String[]{"Vatican City", "Monaco", "San Marino", "Liechtenstein"}, 0),
            
            new Question("Which country has the most time zones?", 
                new String[]{"France", "Russia", "United States", "China"}, 0),
            
            // Climate and Environment
            new Question("What is the imaginary line that divides Earth into Northern and Southern Hemispheres?", 
                new String[]{"Equator", "Prime Meridian", "Tropic of Cancer", "Tropic of Capricorn"}, 0),
            
            new Question("Which country is known as the Land of the Rising Sun?", 
                new String[]{"Japan", "China", "South Korea", "Thailand"}, 0),
            
            new Question("What is the capital of Canada?", 
                new String[]{"Ottawa", "Toronto", "Vancouver", "Montreal"}, 0),
            
            new Question("Which U.S. state is known as the Sunshine State?", 
                new String[]{"Florida", "California", "Texas", "Hawaii"}, 0),
            
            new Question("In which mountain range would you find Mount Everest?", 
                new String[]{"Himalayas", "Andes", "Rockies", "Alps"}, 0)
        };
    }
    
    @Override
    public String getSubjectName() {
        return "GEOGRAPHY";
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