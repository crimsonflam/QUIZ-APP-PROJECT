import javax.swing.JFrame;

public class English_Quiz implements Quiz_Subject {
    private Question[] questions;
    
    public English_Quiz() {
        questions = new Question[] {
            // Grammar
            new Question("Which word is a noun in: 'The quick brown fox jumps over the lazy dog'?", 
                new String[]{"fox", "quick", "jumps", "over"}, 0),
            
            new Question("What is the past tense of 'go'?", 
                new String[]{"went", "goed", "gone", "going"}, 0),
            
            new Question("Which sentence is grammatically correct?", 
                new String[]{"She doesn't like apples.", "She don't like apples.", 
                            "She not like apples.", "She doesn't likes apples."}, 0),
            
            // Vocabulary
            new Question("What is a synonym for 'happy'?", 
                new String[]{"Joyful", "Sad", "Angry", "Tired"}, 0),
            
            new Question("What does 'benevolent' mean?", 
                new String[]{"Kind and generous", "Mean and cruel", "Intelligent", "Wealthy"}, 0),
            
            new Question("Which word means 'fear of heights'?", 
                new String[]{"Acrophobia", "Arachnophobia", "Claustrophobia", "Agoraphobia"}, 0),
            
            // Literature
            new Question("Who wrote 'Romeo and Juliet'?", 
                new String[]{"William Shakespeare", "Charles Dickens", "Jane Austen", "Mark Twain"}, 0),
            
            new Question("In which novel would you find the character Harry Potter?", 
                new String[]{"Harry Potter and the Philosopher's Stone", "The Hobbit", 
                            "The Chronicles of Narnia", "Alice in Wonderland"}, 0),
            
            new Question("Who wrote 'Pride and Prejudice'?", 
                new String[]{"Jane Austen", "Emily Bront\u00EB", "Charlotte Bront\u00EB", "Virginia Woolf"}, 0),
            
            // Parts of Speech
            new Question("What part of speech is the word 'quickly'?", 
                new String[]{"Adverb", "Adjective", "Noun", "Verb"}, 0),
            
            new Question("Which word is a preposition: 'He walked through the door'?", 
                new String[]{"through", "walked", "door", "the"}, 0),
            
            new Question("What is the plural of 'child'?", 
                new String[]{"children", "childs", "childes", "childer"}, 0),
            
            // Spelling
            new Question("Which spelling is correct?", 
                new String[]{"Accommodation", "Acommodation", "Accomodation", "Acomodation"}, 0),
            
            new Question("How do you spell the word meaning 'to receive'?", 
                new String[]{"Receive", "Recieve", "Receve", "Recive"}, 0),
            
            new Question("Which word is misspelled?", 
                new String[]{"Definately", "Definitely", "Separate", "Occurrence"}, 0),
            
            // Figures of Speech
            new Question("What is a metaphor?", 
                new String[]{"Direct comparison without 'like' or 'as'", 
                            "Comparison using 'like' or 'as'", 
                            "Giving human traits to non-human things", 
                            "Exaggeration for effect"}, 0),
            
            new Question("'The world is a stage' is an example of what?", 
                new String[]{"Metaphor", "Simile", "Personification", "Hyperbole"}, 0),
            
            new Question("Who wrote '1984'?", 
                new String[]{"George Orwell", "Aldous Huxley", "Ray Bradbury", "J.R.R. Tolkien"}, 0),
            
            new Question("What is the study of word origins called?", 
                new String[]{"Etymology", "Phonology", "Syntax", "Semantics"}, 0),
            
            new Question("Which tense is: 'I will have finished'?", 
                new String[]{"Future perfect", "Present perfect", "Past perfect", "Simple future"}, 0)
        };
    }
    
    @Override
    public String getSubjectName() {
        return "ENGLISH";
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