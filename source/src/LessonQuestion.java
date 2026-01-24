public class LessonQuestion {
    private String questionText;
    private String[] options;
    private int correctIndex;
    private int userSelection = -1;

    public LessonQuestion(String questionText, String[] options, int correctIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctIndex = correctIndex;
    }

    public String getQuestionText() { return questionText; }
    public String[] getOptions() { return options; }
    public int getCorrectIndex() { return correctIndex; }

    public void setUserSelection(int index) { userSelection = index; }
    public boolean isCorrect() { return userSelection == correctIndex; }
}
