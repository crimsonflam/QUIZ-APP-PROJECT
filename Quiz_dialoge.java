import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Quiz_dialoge extends JDialog {
    private Quiz_Subject subject;
    private ArrayList<ButtonGroup> answerGroups = new ArrayList<>();
    private JLabel timerLabel;
    private Timer quizTimer;
    private int timeRemaining = 300;
    
    // CardLayout for switching between ready screen and quiz
    private CardLayout cardLayout;
    private JPanel cardPanel;
    
    public Quiz_dialoge(Quiz_Subject subject, JFrame parent) {
        super(parent, subject.getSubjectName() + " Quiz", true);
        this.subject = subject;
        
        setSize(800, 600);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());
        
        // Prevent accidental closing
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                confirmExit();
            }
        });
        
        // Create card layout for screens
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        
        // Create both screens
        createReadyScreen();
        createQuizScreen();
        
        // Add both screens to card layout
        cardPanel.add(createReadyScreen(), "READY");
        cardPanel.add(createQuizScreen(), "QUIZ");
        
        // Show ready screen first
        cardLayout.show(cardPanel, "READY");
        
        add(cardPanel, BorderLayout.CENTER);
    }
    
    // ===== READY SCREEN =====
    private JPanel createReadyScreen() {
        JPanel readyPanel = new JPanel(new BorderLayout());
        readyPanel.setBackground(new Color(245, 250, 255));
        
        // Center content panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(245, 250, 255));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        
        // Subject icon
        JLabel subjectIcon = new JLabel(getSubjectIcon(subject.getSubjectName()), SwingConstants.CENTER);
        subjectIcon.setFont(new Font("Arial", Font.BOLD, 72));
        subjectIcon.setForeground(getSubjectColor(subject.getSubjectName()));
        subjectIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Subject title
        JLabel subjectTitle = new JLabel(subject.getSubjectName() + " QUIZ", SwingConstants.CENTER);
        subjectTitle.setFont(new Font("Arial", Font.BOLD, 36));
        subjectTitle.setForeground(new Color(50, 50, 100));
        subjectTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        subjectTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        
        // Quiz info
        JLabel quizInfo = new JLabel(
            "<html><center>" +
            "<b>Quiz Details:</b><br>" +
            "- " + subject.getQuestions().length + " questions<br>" +
            "- 5 minute time limit<br>" +
            "- Multiple choice format<br>" +
            //"• Questions cannot be skipped<br><br>" +
            "<font color='#666666'>Are you ready to begin?</font>" +
            "</center></html>",
            SwingConstants.CENTER
        );
        quizInfo.setFont(new Font("Arial", Font.PLAIN, 16));
        quizInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Warning message
        JLabel warningLabel = new JLabel(
            "<html><center><font color='#CC0000'>" +
            "Once started, timer cannot be paused!<br>" +
            "Make sure you're ready before beginning." +
            "</font></center></html>",
            SwingConstants.CENTER
        );
        warningLabel.setFont(new Font("Arial", Font.BOLD, 14));
        warningLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        warningLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 30, 0));
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        buttonPanel.setBackground(new Color(245, 250, 255));
        
        JButton startButton = new JButton("START QUIZ");
        startButton.setFont(new Font("Arial", Font.BOLD, 18));
        startButton.setPreferredSize(new Dimension(200, 50));
        startButton.setBackground(new Color(50, 150, 50));
        startButton.setForeground(Color.WHITE);
        startButton.setFocusPainted(false);
        startButton.addActionListener(e -> {
            cardLayout.show(cardPanel, "QUIZ");
            startTimer(); // Timer starts ONLY when user clicks start
        });
        
        JButton cancelButton = new JButton("CANCEL");
        cancelButton.setFont(new Font("Arial", Font.PLAIN, 16));
        cancelButton.setPreferredSize(new Dimension(150, 45));
        cancelButton.setBackground(new Color(200, 50, 50));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusPainted(false);
        cancelButton.addActionListener(e -> dispose());
        
        buttonPanel.add(startButton);
        buttonPanel.add(cancelButton);
        
        // Assemble center panel
        centerPanel.add(subjectIcon);
        centerPanel.add(subjectTitle);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        centerPanel.add(quizInfo);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(warningLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        centerPanel.add(buttonPanel);
        
        readyPanel.add(centerPanel, BorderLayout.CENTER);
        
        return readyPanel;
    }
    
    // Helper method for subject icons (using letters)
    private String getSubjectIcon(String subjectName) {
        switch(subjectName) {
            case "MATHEMATICS": return "M";
            case "SCIENCE": return "S";
            case "HISTORY": return "H";
            case "GEOGRAPHY": return "G";
            case "ENGLISH": return "E";
            case "COMPUTER": return "C";
            default: return "?";
        }
    }
    
    // Helper method for subject colors
    private Color getSubjectColor(String subjectName) {
        switch(subjectName) {
            case "MATHEMATICS": return new Color(220, 20, 60);
            case "SCIENCE": return new Color(30, 144, 255);
            case "HISTORY": return new Color(34, 139, 34);
            case "GEOGRAPHY": return new Color(255, 140, 0);
            case "ENGLISH": return new Color(138, 43, 226);
            case "COMPUTER": return new Color(0, 191, 255);
            default: return Color.BLACK;
        }
    }
    
    // ===== QUIZ SCREEN (Your existing quiz UI) =====
    private JPanel createQuizScreen() {
        JPanel quizPanel = new JPanel(new BorderLayout());
        
        // ===== TOP PANEL =====
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(60, 60, 100));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        JLabel title = new JLabel(subject.getSubjectName() + " QUIZ", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        
        timerLabel = new JLabel("05:00", SwingConstants.RIGHT);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        timerLabel.setForeground(Color.GREEN);
        
        topPanel.add(title, BorderLayout.CENTER);
        topPanel.add(timerLabel, BorderLayout.EAST);
        
        // ===== QUESTIONS =====
        JPanel questionsPanel = new JPanel();
        questionsPanel.setLayout(new BoxLayout(questionsPanel, BoxLayout.Y_AXIS));
        questionsPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        
        Question[] questions = subject.getQuestions();
        for (int i = 0; i < questions.length; i++) {
            questionsPanel.add(createQuestionPanel(questions[i], i + 1));
            if (i < questions.length - 1) {
                questionsPanel.add(Box.createRigidArea(new Dimension(0, 15)));
            }
        }
        
        JScrollPane scrollPane = new JScrollPane(questionsPanel);
        
        // ===== CUSTOM SCROLL SPEED =====
        JScrollBar vertical = scrollPane.getVerticalScrollBar();
        vertical.setUnitIncrement(16);
        vertical.setBlockIncrement(50);
        
        // ===== BUTTONS =====
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        JButton submitBtn = new JButton("SUBMIT");
        submitBtn.setFont(new Font("Arial", Font.BOLD, 14));
        submitBtn.setPreferredSize(new Dimension(120, 40));
        submitBtn.setBackground(new Color(50, 150, 50));
        submitBtn.setForeground(Color.WHITE);
        submitBtn.addActionListener(e -> submitQuiz());
        
        JButton cancelBtn = new JButton("CANCEL");
        cancelBtn.setFont(new Font("Arial", Font.PLAIN, 14));
        cancelBtn.setPreferredSize(new Dimension(120, 40));
        cancelBtn.setBackground(new Color(200, 50, 50));
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.addActionListener(e -> confirmExit());
        
        buttonPanel.add(submitBtn);
        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        buttonPanel.add(cancelBtn);
        
        // ===== ASSEMBLE QUIZ PANEL =====
        quizPanel.add(topPanel, BorderLayout.NORTH);
        quizPanel.add(scrollPane, BorderLayout.CENTER);
        quizPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        return quizPanel;
    }
    
    private JPanel createQuestionPanel(Question q, int num) {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 220), 2),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        JLabel questionLabel = new JLabel("<html><b>Q" + num + ":</b> " + q.getQuestionText() + "</html>");
        questionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        
        JPanel optionsPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        ButtonGroup group = new ButtonGroup();
        
        for (int i = 0; i < 4; i++) {
            JRadioButton option = new JRadioButton((char)('A' + i) + ") " + q.getOptions()[i]);
            option.setFont(new Font("Arial", Font.PLAIN, 13));
            option.setActionCommand(String.valueOf(i));
            group.add(option);
            optionsPanel.add(option);
        }
        
        answerGroups.add(group);
        panel.add(questionLabel, BorderLayout.NORTH);
        panel.add(optionsPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    private void startTimer() {
        quizTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeRemaining--;
                updateTimerDisplay();
                
                if (timeRemaining <= 0) {
                    quizTimer.stop();
                    timeUp();
                }
            }
        });
        quizTimer.start();
    }
    
    private void updateTimerDisplay() {
        int minutes = timeRemaining / 60;
        int seconds = timeRemaining % 60;
        String timeText = String.format("%02d:%02d", minutes, seconds);
        timerLabel.setText(timeText);
        
        if (timeRemaining <= 60) {
            timerLabel.setForeground(Color.YELLOW);
        }
        if (timeRemaining <= 30) {
            timerLabel.setForeground(Color.RED);
            timerLabel.setFont(new Font("Arial", Font.BOLD, 26));
        }
    }
    
    private void confirmExit() {
        int choice = JOptionPane.showConfirmDialog(
            this,
            "<html><b>Exit Quiz?</b><br>" +
            "Are you sure you want to leave?<br>" +
            "All your answers will be lost.</html>",
            "Confirm Exit",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );
        
        if (choice == JOptionPane.YES_OPTION) {
            quizTimer.stop();
            dispose();
        }
    }
    
    private void submitQuiz() {
        quizTimer.stop();
        calculateScore();
    }
    
    private void timeUp() {
        JOptionPane.showMessageDialog(this,
            "Time's up! Submitting your quiz...",
            "Time Expired",
            JOptionPane.WARNING_MESSAGE);
        calculateScore();
    }
    
    private void calculateScore() {
        int score = 0;
        int total = subject.getQuestions().length;
        
        for (int i = 0; i < total; i++) {
            ButtonModel selected = answerGroups.get(i).getSelection();
            if (selected != null) {
                int selectedIndex = Integer.parseInt(selected.getActionCommand());
                if (subject.getQuestions()[i].isCorrect(selectedIndex)) {
                    score++;
                }
            }
        }
        
        double percentage = (score * 100.0) / total;
        String grade = getGrade(percentage);
        
        String message = String.format(
            "QUIZ RESULTS\n\n" +
            "Score: %d out of %d\n" +
            "Percentage: %.1f%%\n" +
            "Grade: %s",
            score, total, percentage, grade
        );
        
        JOptionPane.showMessageDialog(this, message, "Results", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }
    
    private String getGrade(double percentage) {
        if (percentage >= 90) return "A+ (Excellent)";
        if (percentage >= 80) return "A (Very Good)";
        if (percentage >= 70) return "B (Good)";
        if (percentage >= 60) return "C (Average)";
        if (percentage >= 50) return "D (Below Average)";
        return "F (Fail)";
    }
}