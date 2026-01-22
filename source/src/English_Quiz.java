import javax.swing.*;
import java.awt.*;

public class English_Quiz implements Quiz_Subject {
    private Question[][] questionsByDifficulty; // [difficulty][questions]
    
    public English_Quiz() {
        // Initialize 3 difficulty levels
        questionsByDifficulty = new Question[3][];
        
        // ========== EASY QUESTIONS (Level 1) ==========
        questionsByDifficulty[0] = new Question[] {
            // Basic Grammar
            new Question("Which sentence is correct?",
                new String[]{"She go to school.", "She goes to school.", "She going to school.", "She to go school."}, 
                1, 1),
            
            new Question("Choose the correct article: ___ apple a day keeps the doctor away.",
                new String[]{"A", "An", "The", "No article needed"}, 
                1, 1),
            
            new Question("What is the plural of 'child'?",
                new String[]{"Childs", "Children", "Childes", "Childen"}, 
                1, 1),
            
            new Question("Which is a noun?",
                new String[]{"Run", "Beautiful", "Happiness", "Quickly"}, 
                2, 1),
            
            new Question("'He ___ a book right now.' Fill in the blank.",
                new String[]{"read", "reads", "is reading", "reading"}, 
                2, 1),
            
            // Basic Vocabulary
            new Question("Synonym for 'happy'?",
                new String[]{"Sad", "Joyful", "Angry", "Tired"}, 
                1, 1),
            
            new Question("Opposite of 'hot'?",
                new String[]{"Warm", "Cold", "Spicy", "Boiling"}, 
                1, 1),
            
            new Question("What does 'benevolent' mean?",
                new String[]{"Kind", "Evil", "Large", "Small"}, 
                0, 1),
            
            new Question("Which word is spelled correctly?",
                new String[]{"Recieve", "Receive", "Recieve", "Receve"}, 
                1, 1),
            
            new Question("What is a 'synonym'?",
                new String[]{"Opposite meaning", "Same meaning", "Rhyming word", "Made-up word"}, 
                1, 1),
            
            // Simple Literature
            new Question("Who wrote 'Romeo and Juliet'?",
                new String[]{"Charles Dickens", "William Shakespeare", "Jane Austen", "Mark Twain"}, 
                1, 1),
            
            new Question("What is the main character in a story called?",
                new String[]{"Antagonist", "Protagonist", "Narrator", "Author"}, 
                1, 1),
            
            new Question("'Peter Piper picked a peck of pickled peppers' is an example of:",
                new String[]{"Metaphor", "Alliteration", "Simile", "Onomatopoeia"}, 
                1, 1),
            
            // Simple Writing
            new Question("Which sentence has correct punctuation?",
                new String[]{"What time is it.", "What time is it?", "What time is it", "What, time is it?"}, 
                1, 1),
            
            new Question("What does a period (.) indicate?",
                new String[]{"A question", "An exclamation", "The end of a sentence", "A pause"}, 
                2, 1),
            
            new Question("Capitalize: 'i live in new york.'",
                new String[]{"I live in New York.", "I Live In New York.", "i live in New york.", "I live in new york."}, 
                0, 1),
            
            // Basic Comprehension
            new Question("What is the setting of a story?",
                new String[]{"The plot", "Where and when it happens", "The characters", "The moral"}, 
                1, 1),
            
            new Question("If someone is 'over the moon', they are:",
                new String[]{"Very sad", "Very happy", "Confused", "Angry"}, 
                1, 1),
            
            new Question("What is a paragraph?",
                new String[]{"A single sentence", "A group of related sentences", "A chapter", "A book"}, 
                1, 1),
            
            new Question("Which is a complete sentence?",
                new String[]{"Running fast.", "The quick brown fox.", "She sings beautifully.", "After the rain stopped."}, 
                2, 1)
        };
        
        // ========== MEDIUM QUESTIONS (Level 2) ==========
        questionsByDifficulty[1] = new Question[] {
            // Intermediate Grammar
            new Question("Which sentence uses the subjunctive mood correctly?",
                new String[]{"I wish I was taller.", "I wish I were taller.", "If I was you, I'd go.", "I suggest he goes now."}, 
                1, 2),
            
            new Question("Identify the dangling modifier: 'Running to catch the bus, my bag fell open.'",
                new String[]{"Running to catch the bus", "my bag fell open", "to catch", "fell open"}, 
                0, 2),
            
            new Question("'Neither the students nor the teacher ___ happy with the results.' Fill in.",
                new String[]{"was", "were", "is", "are"}, 
                0, 2),
            
            new Question("Which is in passive voice?",
                new String[]{"The cat chased the mouse.", "The mouse was chased by the cat.", "She wrote a letter.", "They are building a house."}, 
                1, 2),
            
            // Vocabulary & Word Usage
            new Question("What does 'ephemeral' mean?",
                new String[]{"Lasting a very short time", "Beautiful", "Difficult to understand", "Very large"}, 
                0, 2),
            
            new Question("Choose the correct idiom: 'He really ___ when he saw his test score.'",
                new String[]{"hit the roof", "broke the ice", "spilled the beans", "kicked the bucket"}, 
                0, 2),
            
            new Question("Which pair are homophones?",
                new String[]{"Write/Right", "Book/Look", "Run/Jog", "Hot/Cold"}, 
                0, 2),
            
            new Question("'Her argument was specious.' What does 'specious' mean?",
                new String[]{"Logical and sound", "Misleadingly attractive", "Emotional", "Well-researched"}, 
                1, 2),
            
            // Literature Analysis
            new Question("In 'Moby Dick', the white whale symbolizes:",
                new String[]{"Friendship", "Nature's beauty", "Obsession and the unknowable", "Wealth and success"}, 
                2, 2),
            
            new Question("What literary device: 'The world is a stage'?",
                new String[]{"Simile", "Metaphor", "Hyperbole", "Personification"}, 
                1, 2),
            
            new Question("Who wrote 'Pride and Prejudice'?",
                new String[]{"Emily Brontë", "Charlotte Brontë", "Jane Austen", "Virginia Woolf"}, 
                2, 2),
            
            new Question("What is the tone of this line: 'The wind howled through the lonely night.'",
                new String[]{"Joyful", "Melancholy/Lonely", "Angry", "Excited"}, 
                1, 2),
            
            // Writing & Style
            new Question("Which sentence avoids redundancy?",
                new String[]{"She combined together the ingredients.", "He returned back to his home.", "The final outcome was surprising.", "We collaborated together on the project."}, 
                2, 2),
            
            new Question("What is the purpose of a thesis statement?",
                new String[]{"To conclude the essay", "To introduce the main argument", "To list sources", "To provide examples"}, 
                1, 2),
            
            new Question("Which is a compound-complex sentence?",
                new String[]{"I like apples.", "I like apples, and she likes oranges.", "Although I was tired, I went to work, and I finished my report.", "Running quickly, he caught the bus."}, 
                2, 2),
            
            // Critical Thinking
            new Question("What is the author's purpose in a persuasive essay?",
                new String[]{"To entertain", "To inform", "To convince", "To describe"}, 
                2, 2),
            
            new Question("'All mammals are animals. All dogs are mammals. Therefore, all dogs are animals.' This is:",
                new String[]{"A fallacy", "A valid syllogism", "An analogy", "A metaphor"}, 
                1, 2),
            
            new Question("Identify the rhetorical appeal: '9 out of 10 doctors recommend this product.'",
                new String[]{"Ethos", "Pathos", "Logos", "Kairos"}, 
                0, 2),
            
            new Question("What does 'reading between the lines' mean?",
                new String[]{"Reading quickly", "Understanding the implied meaning", "Reading aloud", "Summarizing text"}, 
                1, 2),
            
            new Question("Which citation style is commonly used in humanities?",
                new String[]{"APA", "MLA", "Chicago", "IEEE"}, 
                1, 2)
        };
        
        // ========== HARD QUESTIONS (Level 3) ==========
        questionsByDifficulty[2] = new Question[] {
            // Advanced Grammar & Syntax
            new Question("Identify the grammatical error: 'The data shows that people whom we thought were loyal have left.'",
                new String[]{"No error", "'whom' should be 'who'", "'shows' should be 'show'", "'have left' should be 'has left'"}, 
                1, 3),
            
            new Question("Which sentence uses the conditional perfect correctly?",
                new String[]{"If I would have known, I would have come.", "If I had known, I would have come.", "If I knew, I would come.", "If I know, I will come."}, 
                1, 3),
            
            new Question("'Not only the students but also the professor ___ confused by the results.' Fill in.",
                new String[]{"was", "were", "is", "are"}, 
                0, 3),
            
            new Question("Which contains a subordinate clause?",
                new String[]{"She left early.", "Because she was tired, she left early.", "She was tired and left early.", "Tired, she left early."}, 
                1, 3),
            
            // Advanced Vocabulary
            new Question("What does 'sesquipedalian' mean?",
                new String[]{"Using long words", "Walking sideways", "Having six feet", "Speaking multiple languages"}, 
                0, 3),
            
            new Question("A 'non sequitur' is:",
                new String[]{"A logical conclusion", "A statement that doesn't follow logically", "A type of poem", "A grammatical error"}, 
                1, 3),
            
            new Question("Which word comes from Greek?",
                new String[]{"Democracy", "Justice", "Liberty", "Government"}, 
                0, 3),
            
            new Question("'He was a veritable Midas.' This is an example of:",
                new String[]{"Simile", "Allusion", "Oxymoron", "Euphemism"}, 
                1, 3),
            
            // Literary Theory & Analysis
            new Question("Who wrote 'The Death of the Author'?",
                new String[]{"Jacques Derrida", "Michel Foucault", "Roland Barthes", "Julia Kristeva"}, 
                2, 3),
            
            new Question("In Freudian literary criticism, the 'id' represents:",
                new String[]{"Moral conscience", "Reality principle", "Primitive desires", "Social norms"}, 
                2, 3),
            
            new Question("Which work exemplifies magical realism?",
                new String[]{"1984 by Orwell", "One Hundred Years of Solitude by Márquez", "Pride and Prejudice by Austen", "Moby Dick by Melville"}, 
                1, 3),
            
            new Question("What is 'deconstruction' in literary theory?",
                new String[]{"Analyzing rhyme scheme", "Examining binary oppositions in text", "Studying author biography", "Counting syllables"}, 
                1, 3),
            
            // Advanced Writing & Rhetoric
            new Question("Which rhetorical device: 'Ask not what your country can do for you...'",
                new String[]{"Anaphora", "Chiasmus", "Metonymy", "Synecdoche"}, 
                1, 3),
            
            new Question("In a formal argument, a 'straw man' is:",
                new String[]{"A strong supporting point", "A misrepresentation of opponent's argument", "The main thesis", "Statistical evidence"}, 
                1, 3),
            
            new Question("What is 'zeugma'?",
                new String[]{"A word that modifies two others in different ways", "Repeating initial consonants", "A contradictory phrase", "An understatement"}, 
                0, 3),
            
            new Question("Which is an example of litotes?",
                new String[]{"He's not unintelligent.", "The thunder roared angrily.", "She was as busy as a bee.", "Time is a thief."}, 
                0, 3),
            
            // Linguistics & Language History
            new Question("Which language family does English belong to?",
                new String[]{"Romance", "Germanic", "Slavic", "Celtic"}, 
                1, 3),
            
            new Question("What was the Great Vowel Shift?",
                new String[]{"A change in consonant sounds", "A major change in vowel pronunciation", "The standardization of spelling", "The creation of new letters"}, 
                1, 3),
            
            new Question("A 'morpheme' is:",
                new String[]{"A unit of sound", "The smallest meaningful language unit", "A type of sentence", "A grammatical error"}, 
                1, 3),
            
            new Question("What is 'code-switching' in linguistics?",
                new String[]{"Changing programming languages", "Alternating between languages/dialects", "Correcting grammar errors", "Translating texts"}, 
                1, 3)
        };
    }
    
    @Override public String getSubjectName() { return "ENGLISH"; }
    @Override
    public Question[] getQuestions(int difficultyLevel) {
        if (difficultyLevel >= 1 && difficultyLevel <= 3) {
            return questionsByDifficulty[difficultyLevel - 1];
        }
        return questionsByDifficulty[1];
    }
    
    @Override public Question[] getQuestions() { return getQuestions(2); }
    
    @Override
    public void startQuiz(JFrame parentFrame, int userId) {
        Quiz_dialoge dialog = new Quiz_dialoge(this, parentFrame, userId, 2);
        dialog.setVisible(true);
    }
}