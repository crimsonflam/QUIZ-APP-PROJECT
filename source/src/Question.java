public class Question {
    private String questionText;
    private String[] options;
    private int correctOption;
    private int difficulty;
    
    public Question(String questionText, String[] options, int correctOption, int difficulty) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
        this.difficulty = difficulty;
    }
    public Question(String questionText, String[] options, int correctOption) {
        this(questionText, options, correctOption, 2);
    }
    // Getters
    public String getQuestionText() { return questionText; }
    public String[] getOptions() { return options; }
    public int getCorrectOption() { return correctOption; }
    public int getDifficulty() { return difficulty; }
    
    // Check if selected answer is correct
    public boolean isCorrect(int selectedOption) {
        return selectedOption == correctOption;
    }
    
    // Get the correct answer text
    public String getCorrectAnswerText() {
        return options[correctOption];
    }
}