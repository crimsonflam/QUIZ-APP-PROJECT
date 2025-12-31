public class Question {
    private String questionText;
    private String[] options;
    private int correctOption; // 0, 1, 2, or 3
    
    public Question(String questionText, String[] options, int correctOption) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
    }
    
    // Getters
    public String getQuestionText() { return questionText; }
    public String[] getOptions() { return options; }
    public int getCorrectOption() { return correctOption; }
    
    // Check if selected answer is correct
    public boolean isCorrect(int selectedOption) {
        return selectedOption == correctOption;
    }
    
    // Get the correct answer text
    public String getCorrectAnswerText() {
        return options[correctOption];
    }
}