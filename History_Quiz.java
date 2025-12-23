import javax.swing.JFrame;

public class History_Quiz implements Quiz_Subject {
    private Question[] questions;
    
    public History_Quiz() {
        questions = new Question[] {
            // Ancient History
            new Question("Which ancient civilization built the pyramids?", 
                new String[]{"Egyptians", "Romans", "Greeks", "Mayans"}, 0),
            
            new Question("Who was the first emperor of Rome?", 
                new String[]{"Augustus", "Julius Caesar", "Nero", "Constantine"}, 0),
            
            new Question("In which year did World War I begin?", 
                new String[]{"1914", "1918", "1939", "1941"}, 0),
            
            // World History
            new Question("Who discovered America in 1492?", 
                new String[]{"Christopher Columbus", "Vasco da Gama", "Marco Polo", "Ferdinand Magellan"}, 0),
            
            new Question("Which empire was ruled by Genghis Khan?", 
                new String[]{"Mongol Empire", "Ottoman Empire", "Roman Empire", "British Empire"}, 0),
            
            new Question("When did the French Revolution begin?", 
                new String[]{"1789", "1776", "1812", "1799"}, 0),
            
            // Modern History
            new Question("Who was the first president of the United States?", 
                new String[]{"George Washington", "Thomas Jefferson", "Abraham Lincoln", "John Adams"}, 0),
            
            new Question("Which wall divided East and West Berlin?", 
                new String[]{"Berlin Wall", "Iron Curtain", "Great Wall", "Wall of Shame"}, 0),
            
            new Question("In which year did World War II end?", 
                new String[]{"1945", "1944", "1946", "1943"}, 0),
            
            new Question("Who wrote the 'Communist Manifesto'?", 
                new String[]{"Karl Marx and Friedrich Engels", "Vladimir Lenin", "Joseph Stalin", "Leon Trotsky"}, 0),
            
            // Leaders and Figures
            new Question("Who was known as the 'Iron Lady'?", 
                new String[]{"Margaret Thatcher", "Indira Gandhi", "Golda Meir", "Queen Victoria"}, 0),
            
            new Question("Which pharaoh's tomb was discovered untouched in 1922?", 
                new String[]{"Tutankhamun", "Ramses II", "Cleopatra", "Khufu"}, 0),
            
            new Question("Who was the last Tsar of Russia?", 
                new String[]{"Nicholas II", "Peter the Great", "Catherine the Great", "Alexander II"}, 0),
            
            // Wars and Battles
            new Question("Where did the D-Day invasion occur during WWII?", 
                new String[]{"Normandy, France", "Pearl Harbor", "Stalingrad", "Iwo Jima"}, 0),
            
            new Question("Which treaty ended World War I?", 
                new String[]{"Treaty of Versailles", "Treaty of Paris", "Treaty of Ghent", "Treaty of Tordesillas"}, 0),
            
            // Inventions and Discoveries
            new Question("Who invented the telephone?", 
                new String[]{"Alexander Graham Bell", "Thomas Edison", "Nikola Tesla", "Guglielmo Marconi"}, 0),
            
            new Question("When did the Industrial Revolution begin?", 
                new String[]{"Late 18th century", "Early 17th century", "Mid 19th century", "Early 20th century"}, 0),
            
            new Question("Which ancient wonder was located in Babylon?", 
                new String[]{"Hanging Gardens", "Great Pyramid", "Colossus of Rhodes", "Lighthouse of Alexandria"}, 0),
            
            new Question("Who was the first man to walk on the moon?", 
                new String[]{"Neil Armstrong", "Buzz Aldrin", "Yuri Gagarin", "John Glenn"}, 0),
            
            new Question("Which civilization invented paper?", 
                new String[]{"Chinese", "Egyptians", "Greeks", "Romans"}, 0)
        };
    }
    
    @Override
    public String getSubjectName() {
        return "HISTORY";
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