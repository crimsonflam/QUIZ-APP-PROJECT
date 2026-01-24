
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
    private int timeTaken = 0; // Track actual time taken
    private int userId; // User ID (0 for guest)
    private scoreDAO scoreDao; // For saving scores
    private boolean scoreSaved = false; 
    private dashboard dashboardRef;

    private int difficultyLevel; // 1=easy, 2=medium, 3=hard
    private String[] difficultyNames = {"Easy", "Medium", "Hard"};
    private Color[] difficultyColors = {
        new Color(76, 175, 80),    // Green for Easy
        new Color(255, 152, 0),    // Orange for Medium
        new Color(244, 67, 54)     // Red for Hard
    };
    private JButton saveButton; 
    // CardLayout for switching between ready screen and quiz
    private CardLayout cardLayout;
    private JPanel cardPanel;
    
    public Quiz_dialoge(Quiz_Subject subject, JFrame parent, int userId, int difficultyLevel) {
        super(parent, subject.getSubjectName() + " Quiz", true);
        this.subject = subject;
        this.userId = userId;
        if (userId > 0) {
            // For logged-in users, get from database
            try {
                difficultyDAO diffDAO = new difficultyDAO();
                int dbLevel = diffDAO.getUserLevel(userId, subject.getSubjectName());
                this.difficultyLevel = dbLevel;
                System.out.println("Loaded difficulty from DB: " + dbLevel + " for " + subject.getSubjectName());
            } catch (Exception e) {
                System.out.println("Error getting difficulty from DB, using provided: " + e.getMessage());
                this.difficultyLevel = difficultyLevel;
            }
        } else {
            // For guests, use provided level
            this.difficultyLevel = difficultyLevel;
        }

        if (this.difficultyLevel < 1) this.difficultyLevel = 1;
        if (this.difficultyLevel > 3) this.difficultyLevel = 3;
        
        // Initialize scoreDAO if user is logged in
        if (userId > 0) {
            scoreDao = new scoreDAO();
        }
        
        setSize(850, 700);
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
        
        // Add both screens to card layout
        cardPanel.add(createReadyScreen(), "READY");
        cardPanel.add(createQuizScreen(), "QUIZ");
        
        // Show ready screen first
        cardLayout.show(cardPanel, "READY");
        
        add(cardPanel, BorderLayout.CENTER);
        // pack();
        // setLocationRelativeTo(parent);
        // setResizable(true);
    }
    private String getDifficultyName() {
        if (difficultyLevel >= 1 && difficultyLevel <= 3) {
            return difficultyNames[difficultyLevel - 1];
        }
        return "Medium";
    }
    
    private Color getDifficultyColor() {
        if (difficultyLevel >= 1 && difficultyLevel <= 3) {
            return difficultyColors[difficultyLevel - 1];
        }
        return difficultyColors[1]; // Default to medium
    }
    
    private String getDifficultyColorHex() {
        Color color = getDifficultyColor();
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }
    
    // ===== READY SCREEN =====
    private JPanel createReadyScreen() {
        JPanel readyPanel = new JPanel(new BorderLayout());
        readyPanel.setBackground(new Color(245, 250, 255));

        // Center content panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(245, 250, 255));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        
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
        
        // ===== DIFFICULTY DISPLAY =====
        JPanel difficultyPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        difficultyPanel.setOpaque(false);

        JLabel difficultyLabel = new JLabel("Difficulty:", SwingConstants.CENTER);
        difficultyLabel.setFont(new Font("Arial", Font.BOLD, 16));
        difficultyLabel.setForeground(new Color(80, 80, 80));

        JLabel difficultyValue = new JLabel(getDifficultyName(), SwingConstants.CENTER);
        difficultyValue.setFont(new Font("Arial", Font.BOLD, 18));
        difficultyValue.setForeground(getDifficultyColor());
        difficultyValue.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(getDifficultyColor(), 2),
            BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));

        difficultyPanel.add(difficultyLabel);
        difficultyPanel.add(difficultyValue);
        // ===== DIFFICULTY ADJUSTMENT BUTTONS (for logged-in users) =====
      if (userId > 0) {
    JPanel adjustmentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
    adjustmentPanel.setOpaque(false);
    adjustmentPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
    centerPanel.add(adjustmentPanel);
    JLabel adjustLabel = new JLabel("Change Difficulty:");
    adjustLabel.setFont(new Font("Arial", Font.PLAIN, 14));
    adjustLabel.setForeground(new Color(80, 80, 80));
    
    // Create styled difficulty buttons
    String[] levels = {"Easy", "Medium", "Hard"};
    Color[] levelColors = {
        new Color(76, 175, 80),    // Green
        new Color(255, 152, 0),    // Orange  
        new Color(244, 67, 54)     // Red
    };
    
    for (int i = 0; i < 3; i++) {
        final int level = i + 1;
        JButton levelButton = new JButton(levels[i]);
        levelButton.setFont(new Font("Arial", Font.BOLD, 12));
        levelButton.setPreferredSize(new Dimension(80, 30));
        
        // Style based on current level
        if (level == difficultyLevel) {
            levelButton.setBackground(levelColors[i]);
            levelButton.setForeground(Color.WHITE);
            levelButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        } else {
            levelButton.setBackground(levelColors[i].brighter());
            levelButton.setForeground(Color.BLACK);
            levelButton.setBorder(BorderFactory.createLineBorder(levelColors[i], 1));
        }
        
        levelButton.addActionListener(e -> {
            // Update difficulty
            difficultyLevel = level;
            
            // Update database
            try {
                difficultyDAO diffDAO = new difficultyDAO();
                diffDAO.updateUserLevel(userId, subject.getSubjectName(), difficultyLevel);
                
                // Update display
                difficultyValue.setText(getDifficultyName());
                difficultyValue.setForeground(getDifficultyColor());
                
                // Update button styles
                Component[] buttons = adjustmentPanel.getComponents();
                for (Component comp : buttons) {
                    if (comp instanceof JButton) {
                        JButton btn = (JButton) comp;
                        int btnLevel = Integer.parseInt(btn.getActionCommand());
                        if (btnLevel == difficultyLevel) {
                            btn.setBackground(levelColors[btnLevel-1]);
                            btn.setForeground(Color.WHITE);
                            btn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
                        } else {
                            btn.setBackground(levelColors[btnLevel-1].brighter());
                            btn.setForeground(Color.BLACK);
                            btn.setBorder(BorderFactory.createLineBorder(levelColors[btnLevel-1], 1));
                        }
                    }
                }
                    
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                    "Error updating difficulty: " + ex.getMessage(),
                    "Update Failed",
                    JOptionPane.ERROR_MESSAGE);
            }
        });
        
        levelButton.setActionCommand(String.valueOf(level));
        adjustmentPanel.add(levelButton);
    }
    
    centerPanel.add(adjustmentPanel);
}
        // Quiz info with user status
        String userStatus = (userId > 0) ? " Scores will be saved to your account" : " Guest mode - scores won't be saved";
        Color statusColor = (userId > 0) ? new Color(0, 150, 0) : new Color(200, 100, 0);
        
        JLabel quizInfo = new JLabel(
            "<html><center>" +
            "<b>Quiz Details:</b><br>" +
            "- " + subject.getQuestions(difficultyLevel).length + " questions<br>" +
            "- 5 minute time limit<br>" +
            "- Multiple choice format<br>" +
            "<br><font color='" + String.format("#%02x%02x%02x", statusColor.getRed(), 
                statusColor.getGreen(), statusColor.getBlue()) + "'>" +
            userStatus + "</font><br><br>" +
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
        // FIXED: Button panel with proper spacing
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(new Color(245, 250, 255));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        // Button container for horizontal alignment
        JPanel buttonContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 0));
        buttonContainer.setBackground(new Color(245, 250, 255));

        JButton startButton = new JButton("START QUIZ");
        startButton.setFont(new Font("Arial", Font.BOLD, 18));
        startButton.setPreferredSize(new Dimension(200, 50));
        startButton.setBackground(new Color(50, 150, 50));
        startButton.setForeground(Color.WHITE);
        startButton.setFocusPainted(false);
        startButton.addActionListener(e -> {
            answerGroups.clear();
            cardPanel.remove(1);
            cardPanel.add(createQuizScreen(), "QUIZ");
            cardLayout.show(cardPanel, "QUIZ");
            startTimer(); // Timer starts ONLY when user clicks start
            cardPanel.revalidate();  // Important!
            cardPanel.repaint();
        });

        JButton cancelButton = new JButton("CANCEL");
        cancelButton.setFont(new Font("Arial", Font.PLAIN, 16));
        cancelButton.setPreferredSize(new Dimension(150, 45));
        cancelButton.setBackground(new Color(200, 50, 50));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusPainted(false);
        cancelButton.addActionListener(e -> dispose());

        buttonContainer.add(startButton);
        buttonContainer.add(cancelButton);
        buttonPanel.add(buttonContainer);
        
        // Assemble center panel
        centerPanel.add(subjectIcon);
        centerPanel.add(subjectTitle);
        centerPanel.add(difficultyPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        centerPanel.add(quizInfo);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(warningLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        centerPanel.add(buttonPanel);
        
//         JPanel wrapperPanel = new JPanel(new GridBagLayout());
// wrapperPanel.setBackground(new Color(245, 250, 255));
// wrapperPanel.add(centerPanel);
// readyPanel.add(wrapperPanel, BorderLayout.CENTER);
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
        answerGroups.clear();
        JPanel quizPanel = new JPanel(new BorderLayout());
        
        // ===== TOP PANEL =====
JPanel topPanel = new JPanel(new BorderLayout());
topPanel.setBackground(new Color(60, 60, 100));
topPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

// Left side: Difficulty indicator
JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
leftPanel.setBackground(new Color(60, 60, 100));

// Difficulty badge
JPanel difficultyBadge = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 2));
difficultyBadge.setBackground(getDifficultyColor());
difficultyBadge.setBorder(BorderFactory.createCompoundBorder(
    BorderFactory.createLineBorder(Color.WHITE, 1),
    BorderFactory.createEmptyBorder(3, 10, 3, 10)
));

JLabel difficultyLabel = new JLabel("Level: " + getDifficultyName());
difficultyLabel.setFont(new Font("Arial", Font.BOLD, 12));
difficultyLabel.setForeground(Color.WHITE);
difficultyBadge.add(difficultyLabel);

// User indicator
String userIndicator = (userId > 0) ? "User" : "Guest";
JLabel userLabel = new JLabel(userIndicator, SwingConstants.LEFT);
userLabel.setFont(new Font("Arial", Font.PLAIN, 14));
userLabel.setForeground(new Color(200, 200, 200));

leftPanel.add(userLabel);
leftPanel.add(Box.createRigidArea(new Dimension(10, 0)));
leftPanel.add(difficultyBadge);

// Center: Title
JLabel title = new JLabel(subject.getSubjectName() + " QUIZ", SwingConstants.CENTER);
title.setFont(new Font("Arial", Font.BOLD, 28));
title.setForeground(Color.WHITE);

// Right side: Timer
timerLabel = new JLabel("05:00", SwingConstants.RIGHT);
timerLabel.setFont(new Font("Arial", Font.BOLD, 24));
timerLabel.setForeground(Color.GREEN);

topPanel.add(leftPanel, BorderLayout.WEST);
topPanel.add(title, BorderLayout.CENTER);
topPanel.add(timerLabel, BorderLayout.EAST);
        
        // ===== QUESTIONS =====
        JPanel questionsPanel = new JPanel();
        questionsPanel.setLayout(new BoxLayout(questionsPanel, BoxLayout.Y_AXIS));
        questionsPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        
        Question[] questions = subject.getQuestions(difficultyLevel);
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
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        
        JButton submitBtn = new JButton("SUBMIT");
        submitBtn.setFont(new Font("Arial", Font.BOLD, 14));
        submitBtn.setPreferredSize(new Dimension(120, 40));
        submitBtn.setBackground(new Color(50, 150, 50));
        submitBtn.setForeground(Color.WHITE);
        submitBtn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("SUBMIT BUTTON CLICKED!");
            calculateScore();  // THIS SHOWS SCORE AND ANSWERS
            }
        });
        
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
        timeTaken = 0;
        quizTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeRemaining--;
                timeTaken++;
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
            if (quizTimer != null) {
                quizTimer.stop();
            }
            dispose();
        }
    }
    
    private void timeUp() {
        JOptionPane.showMessageDialog(this,
            "Time's up! Submitting your quiz...",
            "Time Expired",
            JOptionPane.WARNING_MESSAGE);
        calculateScore();
    }
    
    private void calculateScore() {
        if (quizTimer != null) {
            quizTimer.stop();
        }
        
        int score = 0;
        int total = subject.getQuestions(difficultyLevel).length;
        
        
        // Calculate score
        for (int i = 0; i < total; i++) {
            ButtonGroup group = answerGroups.get(i);
            ButtonModel selected = group.getSelection();
            
            if (selected != null) {
                int selectedIndex = Integer.parseInt(selected.getActionCommand());
                if (subject.getQuestions(difficultyLevel)[i].isCorrect(selectedIndex)) {
                    score++;
                }
            }
        }
        
        double percentage = (score * 100.0) / total;
        
        // Show the enhanced results screen
        showResultsScreen(score, total, percentage);
    }

    private void showResultsScreen(int score, int total, double percentage) {
        // Create results dialog
        JDialog resultsDialog = new JDialog(this, "Quiz Results", true);
        resultsDialog.setSize(900, 650); // Slightly larger for more info
        resultsDialog.setLocationRelativeTo(this);
        resultsDialog.setLayout(new BorderLayout());
        
        // Calculate time taken (in seconds)
        int actualTimeTaken = timeTaken;
        int timeLeft = 300 - actualTimeTaken;
        
        // ===== HEADER PANEL =====
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(70, 130, 180));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        
        String grade = getGrade(percentage);
        Color gradeColor = getGradeColor(percentage);
        
        JLabel scoreLabel = new JLabel(
            String.format("<html><center><font size='+2'>%s QUIZ RESULTS</font><br>" +
                         "<font size='+1'>Score: %d/%d (%.1f%%) - %s</font></center></html>", 
                         subject.getSubjectName(), score, total, percentage, grade),
            SwingConstants.CENTER
        );
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scoreLabel.setForeground(Color.WHITE);
        headerPanel.add(scoreLabel, BorderLayout.CENTER);
        
        // ===== STATS PANEL =====
        JPanel statsPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        statsPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        statsPanel.setBackground(new Color(240, 245, 250));
        
        // Time taken stat
        JPanel timePanel = createStatPanel(" Time Taken", 
            String.format("%d:%02d", actualTimeTaken / 60, actualTimeTaken % 60), 
            "seconds");
        
        // Performance stat
        String performance = getPerformanceText(percentage);
        JPanel perfPanel = createStatPanel("Performance", performance, 
            percentage >= 70 ? "Good job!" : "Keep practicing!");
        
        // Save status stat
        // Save status stat
        String saveStatus = (userId > 0) ? " Saved to Profile" : "Guest Mode";
        Color saveColor = (userId > 0) ? new Color(0, 150, 0) : new Color(150, 150, 0);
        String saveSubtitle = (userId > 0) ? "Available in history" : "Not saved";

        // Create a custom panel instead of using createStatPanel
        JPanel savePanel = new JPanel();
        savePanel.setLayout(new BoxLayout(savePanel, BoxLayout.Y_AXIS));
        savePanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 220), 1),
            BorderFactory.createEmptyBorder(15, 10, 15, 10)
        ));
        savePanel.setBackground(Color.WHITE);
        savePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel saveTitle = new JLabel(" Result Status", SwingConstants.CENTER);
        saveTitle.setFont(new Font("Arial", Font.PLAIN, 12));
        saveTitle.setForeground(new Color(100, 100, 100));

        JLabel saveValue = new JLabel(saveStatus, SwingConstants.CENTER);
        saveValue.setFont(new Font("Arial", Font.BOLD, 18));
        saveValue.setForeground(saveColor);  // Directly set color

        JLabel saveSub = new JLabel(saveSubtitle, SwingConstants.CENTER);
        saveSub.setFont(new Font("Arial", Font.PLAIN, 11));
        saveSub.setForeground(new Color(150, 150, 150));

        savePanel.add(saveTitle);
        savePanel.add(Box.createRigidArea(new Dimension(0, 5)));
        savePanel.add(saveValue);
        savePanel.add(Box.createRigidArea(new Dimension(0, 3)));
        savePanel.add(saveSub);

        statsPanel.add(timePanel);
        statsPanel.add(perfPanel);
        statsPanel.add(savePanel);
        
        // ===== QUESTIONS REVIEW =====
        JPanel reviewPanel = new JPanel();
        reviewPanel.setLayout(new BoxLayout(reviewPanel, BoxLayout.Y_AXIS));
        reviewPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        
        for (int i = 0; i < total; i++) {
            reviewPanel.add(createQuestionReview(i));
            if (i < total - 1) {
                reviewPanel.add(Box.createRigidArea(new Dimension(0, 15)));
            }
        }
        
        JScrollPane scrollPane = new JScrollPane(reviewPanel);
        JScrollBar vertical = scrollPane.getVerticalScrollBar();
        vertical.setUnitIncrement(16);
        
        // ===== BUTTON PANEL =====
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        
        // Save score button (only for logged in users)
        // to change to go to lesson
        if (userId > 0) {
            // JButton saveButton = new JButton(" SAVE SCORE");
            // saveButton.setFont(new Font("Arial", Font.BOLD, 14));
            // saveButton.setPreferredSize(new Dimension(180, 45));
            // saveButton.setBackground(new Color(0, 150, 0));
            // saveButton.setForeground(Color.WHITE);
            // saveButton.setFocusPainted(false);
            // saveButton.addActionListener(e -> {
            //     saveScoreToDatabase(score, total, percentage, actualTimeTaken);
            //     saveButton.setEnabled(false);
            //     saveButton.setText(" SCORE SAVED");
            //     saveButton.setBackground(new Color(100, 100, 100));
            // });
            // buttonPanel.add(saveButton);
        } else {
            // Register prompt for guests
            //change to register to access lessons
            JButton registerButton = new JButton(" REGISTER TO SAVE");
            registerButton.setFont(new Font("Arial", Font.BOLD, 14));
            registerButton.setPreferredSize(new Dimension(220, 45));
            registerButton.setBackground(new Color(30, 144, 255));
            registerButton.setForeground(Color.WHITE);
            registerButton.setFocusPainted(false);
            registerButton.addActionListener(e -> {
                resultsDialog.dispose();
                this.dispose();
                // Open registration screen (you'll need to implement this)
                JOptionPane.showMessageDialog(this, 
                    "Register to save your scores and track your progress!",
                    "Save Your Results", 
                    JOptionPane.INFORMATION_MESSAGE);
            });
            buttonPanel.add(registerButton);
        }
        
        JButton closeButton = new JButton(" exit ");
closeButton.setFont(new Font("Arial", Font.BOLD, 14));
closeButton.setPreferredSize(new Dimension(150, 45));
closeButton.setBackground(new Color(50, 150, 50));  // Green instead of red
closeButton.setForeground(Color.WHITE);
closeButton.setFocusPainted(false);
closeButton.addActionListener(e -> {
    resultsDialog.dispose();     
    dispose();  // Close quiz dialog
});
        
        buttonPanel.add(closeButton);
        
        // ===== ASSEMBLE DIALOG =====
        resultsDialog.add(headerPanel, BorderLayout.NORTH);
        resultsDialog.add(statsPanel, BorderLayout.CENTER);
        resultsDialog.add(scrollPane, BorderLayout.CENTER);
        resultsDialog.add(buttonPanel, BorderLayout.SOUTH);
        
        // Automatically save score for logged in users
        if (userId > 0) {
            saveScoreToDatabase(score, total, percentage, actualTimeTaken);
        }
        
        resultsDialog.setVisible(true);
    }
    
    // Helper method to create stat panels
    private JPanel createStatPanel(String title, String value, String subtitle) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 220), 1),
            BorderFactory.createEmptyBorder(15, 10, 15, 10)
        ));
        panel.setBackground(Color.WHITE);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        titleLabel.setForeground(new Color(100, 100, 100));
        
        JLabel valueLabel = new JLabel(value, SwingConstants.CENTER);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 18));
        valueLabel.setForeground(new Color(50, 50, 100));
        
        JLabel subtitleLabel = new JLabel(subtitle, SwingConstants.CENTER);
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        subtitleLabel.setForeground(new Color(150, 150, 150));
        
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(valueLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 3)));
        panel.add(subtitleLabel);
        
        return panel;
    }
    
    private JPanel createQuestionReview(int questionIndex) {
        Question q = subject.getQuestions(difficultyLevel)[questionIndex];
        ButtonGroup group = answerGroups.get(questionIndex);
        ButtonModel selected = group.getSelection();
        
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        
        // Determine if answer was correct
        boolean isCorrect = false;
        String userAnswer = "Not answered";
        
        if (selected != null) {
            int selectedIndex = Integer.parseInt(selected.getActionCommand());
            userAnswer = q.getOptions()[selectedIndex];
            isCorrect = q.isCorrect(selectedIndex);
        }
        
        // Set border color based on correctness
        Color borderColor = isCorrect ? new Color(50, 200, 50) : 
                         (selected == null ? new Color(200, 200, 100) : new Color(220, 50, 50));
        
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(borderColor, 3),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        // Question number and text
        JLabel questionLabel = new JLabel(
            "<html><b>Q" + (questionIndex + 1) + ":</b> " + q.getQuestionText() + "</html>"
        );
        questionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        
        // Options panel
        JPanel optionsPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        String[] options = q.getOptions();
        
        for (int i = 0; i < 4; i++) {
            JLabel optionLabel = new JLabel(
                "<html><span style='font-size:13px'>" + 
                (char)('A' + i) + ") " + options[i] + 
                (i == q.getCorrectOption() ? " " : "") +
                "</span></html>"
            );
            
            // Color coding
            if (i == q.getCorrectOption()) {
                optionLabel.setForeground(new Color(0, 150, 0));
                optionLabel.setBackground(new Color(230, 255, 230));
            } else if (selected != null && i == Integer.parseInt(selected.getActionCommand())) {
                optionLabel.setForeground(Color.RED);
                optionLabel.setBackground(new Color(255, 230, 230));
            }
            
            optionLabel.setOpaque(true);
            optionsPanel.add(optionLabel);
        }
        
        // Result summary
        String resultText = isCorrect ? " Correct" : 
                           (selected == null ? "? Not answered" : " Wrong");
        Color resultColor = isCorrect ? new Color(0, 150, 0) : 
                           (selected == null ? new Color(150, 150, 0) : new Color(220, 0, 0));
        
        JLabel resultLabel = new JLabel(
            "<html><b><font color='" + String.format("#%02x%02x%02x", 
                resultColor.getRed(), resultColor.getGreen(), resultColor.getBlue()) + 
                "'>" + resultText + "</font></b>" + 
                (selected != null && !isCorrect ? 
                 "<br>Your answer: <font color='#CC0000'>" + userAnswer + "</font>" : "") +
                "<br>Correct answer: <font color='#006600'>" + q.getCorrectAnswerText() + "</font></html>"
        );
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        
        panel.add(questionLabel, BorderLayout.NORTH);
        panel.add(optionsPanel, BorderLayout.CENTER);
        panel.add(resultLabel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    // Save score to database
    private void saveScoreToDatabase(int score, int total, double percentage, int timeTaken) {
        if (scoreSaved) {
            System.out.println("Score already saved, skipping...");
            return;  // Don't save again
        }  
        
        if (userId > 0 && scoreDao != null) {
            try {
                // Save score with difficulty level
                int scoreId = scoreDao.saveScore(userId, subject.getSubjectName(), 
                                                score, total, timeTaken);
                
                if (scoreId > 0) {
                    scoreSaved = true;
                    System.out.println("Score saved! ID: " + scoreId);
                    
                    // ===== UPDATE USER'S DIFFICULTY LEVEL =====
                    try {
                        difficultyDAO diffDAO = new difficultyDAO();
                        int currentLevel = difficultyLevel;
                        int newLevel = diffDAO.calculateNewLevel(currentLevel, percentage);
                        //System.out.println("Current Level: " + currentLevel + ", New Level: " + newLevel);
                        if (newLevel != currentLevel) {
                            // Update database with new level
                            diffDAO.updateUserLevel(userId, subject.getSubjectName(), newLevel);
                            
                            // Show level change message
                            String[] levelNames = {"Easy", "Medium", "Hard"};
                            if (newLevel > currentLevel) {
                                JOptionPane.showMessageDialog(this,
                                    "LEVEL UP!\n" +
                                    subject.getSubjectName() + " difficulty increased!\n" +
                                    levelNames[currentLevel-1] + " ->" + levelNames[newLevel-1],
                                    "Level Increased!",
                                    JOptionPane.INFORMATION_MESSAGE);
                            } else if (newLevel < currentLevel) {
                                JOptionPane.showMessageDialog(this,
                                    "Difficulty Adjusted\n" +
                                    subject.getSubjectName() + " difficulty decreased.\n" +
                                    levelNames[currentLevel-1] + " -> " + levelNames[newLevel-1] +
                                    "\n\nKeep practicing!",
                                    "Level Adjusted",
                                    JOptionPane.WARNING_MESSAGE);
                            } 
                        }
                    } catch (Exception e) {
                        System.out.println("Error updating difficulty level: " + e.getMessage());
                    }
                    // ===========================================
                    
                    // Disable save button
                    if (saveButton != null) {
                        saveButton.setEnabled(false);
                        saveButton.setText(" SCORE SAVED");
                        saveButton.setBackground(new Color(100, 100, 100));
                    }
                    
                    JOptionPane.showMessageDialog(this, 
                        subject.getSubjectName() + " Difficulty remains the same\n",
                        "No Level Change",
                        JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, 
                    "Error saving score: " + e.getMessage(),
                    "Save Failed",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    

    }
    
    private String getGrade(double percentage) {
        if (percentage >= 90) return "A+";
        if (percentage >= 80) return "A";
        if (percentage >= 70) return "B";
        if (percentage >= 60) return "C";
        if (percentage >= 50) return "D";
        return "F";
    }
    
    private Color getGradeColor(double percentage) {
        if (percentage >= 90) return new Color(0, 150, 0);
        if (percentage >= 80) return new Color(100, 200, 0);
        if (percentage >= 70) return new Color(200, 200, 0);
        if (percentage >= 60) return new Color(255, 140, 0);
        if (percentage >= 50) return new Color(255, 100, 0);
        return new Color(220, 0, 0);
    }
    
    private String getPerformanceText(double percentage) {
        if (percentage >= 90) return "Excellent!";
        if (percentage >= 80) return "Very Good";
        if (percentage >= 70) return "Good";
        if (percentage >= 60) return "Average";
        if (percentage >= 50) return "Below Average";
        return "Needs Improvement";
    }
}