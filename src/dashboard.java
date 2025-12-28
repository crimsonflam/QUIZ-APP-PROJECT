

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class dashboard extends JFrame {
    private int userId;
    private userDAO userDao;
    private scoreDAO scoreDao;
    private JLabel welcomeLabel, lastLoginLabel;
    private JTextArea statsArea, historyArea;
    
    public dashboard(int userId) {
        this.userId = userId;
        userDao = new userDAO();
        scoreDao = new scoreDAO();
        initComponents();
        setupLayout();
        loadUserData();
        setupListeners();  // ADD THIS LINE!
    }
    
    private void initComponents() {
        setTitle("Quiz Master - Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550, 650);
        setLocationRelativeTo(null);
        
        welcomeLabel = new JLabel();
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        
        lastLoginLabel = new JLabel();
        lastLoginLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        lastLoginLabel.setForeground(Color.GRAY);
        
        statsArea = new JTextArea(5, 30);
        statsArea.setEditable(false);
        statsArea.setFont(new Font("Arial", Font.PLAIN, 14));
        statsArea.setBackground(new Color(245, 245, 245));
        statsArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        historyArea = new JTextArea(8, 30);
        historyArea.setEditable(false);
        historyArea.setFont(new Font("Arial", Font.PLAIN, 12));
        historyArea.setBackground(new Color(245, 245, 245));
        historyArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout(10, 10));
        
        // Header panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        headerPanel.setBackground(new Color(70, 130, 180));
        
        headerPanel.add(welcomeLabel, BorderLayout.NORTH);
        headerPanel.add(lastLoginLabel, BorderLayout.SOUTH);
        
        // Statistics panel
        JPanel statsPanel = new JPanel(new BorderLayout());
        statsPanel.setBorder(BorderFactory.createTitledBorder(" YOUR STATISTICS"));
        statsPanel.add(new JScrollPane(statsArea), BorderLayout.CENTER);
        
        // History panel
        JPanel historyPanel = new JPanel(new BorderLayout());
        historyPanel.setBorder(BorderFactory.createTitledBorder(" RECENT QUIZZES"));
        historyPanel.add(new JScrollPane(historyArea), BorderLayout.CENTER);
        
        // Button panel 
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton startQuizButton = new JButton("START NEW QUIZ");
        JButton historyButton = new JButton("VIEW FULL HISTORY");
        JButton refreshButton = new JButton("REFRESH");
        JButton logoutButton = new JButton("LOGOUT");

        // Style buttons
        styleButton(startQuizButton, new Color(50, 150, 50));
        styleButton(historyButton, new Color(30, 144, 255));
        styleButton(refreshButton, new Color(255, 140, 0));  
        styleButton(logoutButton, new Color(220, 20, 60));

        buttonPanel.add(startQuizButton);
        buttonPanel.add(historyButton);
        buttonPanel.add(refreshButton);
        buttonPanel.add(logoutButton);
        
        // Action listeners are already set in setupListeners()
        
        // Main content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        contentPanel.add(headerPanel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(statsPanel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(historyPanel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        add(contentPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void setupListeners() {
        // Since we can't directly access the buttons from here,
        // we need to find them in the container
        
        Container contentPane = getContentPane();
        Component[] components = contentPane.getComponents();
        
        for (Component comp : components) {
            if (comp instanceof JPanel) {
                findAndSetupButtons((JPanel) comp);
            }
        }
    }
    
    private void findAndSetupButtons(JPanel panel) {
        for (Component comp : panel.getComponents()) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                String text = button.getText();
                
                switch (text) {
                    case "START NEW QUIZ":
                        button.addActionListener(e -> startNewQuiz());
                        break;
                    case "VIEW FULL HISTORY":
                        button.addActionListener(e -> showFullHistory());
                        break;
                    case "REFRESH PROFILE":
                        button.addActionListener(e -> refreshDashboard());
                        break;
                    case "LOGOUT":
                        button.addActionListener(e -> logout());
                        break;
                }
            } else if (comp instanceof JPanel) {
                findAndSetupButtons((JPanel) comp);
            }
        }
    }
    
    
    private void styleButton(JButton button, Color color) {
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(color);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(color.brighter());
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(color);
            }
        });
    }
    
    private void loadUserData() {
        System.out.println("=== Loading user data ===");
    try {
        // ... rest of your existing code ...
        
    } catch (Exception e) {
        System.out.println("ERROR in loadUserData: " + e.getMessage());
        e.printStackTrace();
        statsArea.setText("Error loading data. Click REFRESH to try again.");
        historyArea.setText("Error loading history.");
    }
        
        // Load user info
        userDAO.User user = userDao.getUserInfo(userId);
        if (user != null) {
            welcomeLabel.setText("<html><font color='white'>Welcome back, " + user.getUsername() + "!</font></html>");
            
            if (user.getLastLogin() != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy HH:mm");
                lastLoginLabel.setText("<html><font color='#CCCCCC'>Last login: " + sdf.format(user.getLastLogin()) + "</font></html>");
            } else {
                lastLoginLabel.setText("<html><font color='#CCCCCC'>First login!</font></html>");
            }
        }
        
        // Load statistics
        scoreDAO.UserStatistics stats = scoreDao.getUserStatistics(userId);
        StringBuilder statsText = new StringBuilder();
        if (stats.getTotalQuizzes() > 0) {
            statsText.append("- Total quizzes: ").append(stats.getTotalQuizzes()).append("\n");
            statsText.append("- Average score: ").append(String.format("%.1f%%", stats.getAverageScore())).append("\n");
            statsText.append("- Best score: ").append(String.format("%.1f%%", stats.getBestScore())).append("\n");
            if (stats.getBestSubject() != null) {
                statsText.append("- Best subject: ").append(stats.getBestSubject());
            }
        } else {
            statsText.append("No quizzes taken yet.\n");
            statsText.append("Start your first quiz!");
        }
        statsArea.setText(statsText.toString());
        
        // Load recent history
        List<scoreDAO.QuizResult> history = scoreDao.getUserHistory(userId);
        StringBuilder historyText = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd");
        
        int count = 0;
        for (scoreDAO.QuizResult result : history) {
            if (count >= 9) break;
            historyText.append(String.format("%d. %s - %d/%d (%.0f%%) - %s\n",
                ++count,
                result.getSubject(),
                result.getScore(),
                result.getTotal(),
                result.getPercentage(),
                sdf.format(result.getCompletedAt())
            ));
        }
        
        if (historyText.length() == 0) {
            historyText.append("No quizzes taken yet.\n");
            historyText.append("Start your first quiz!");
        }
        historyArea.setText(historyText.toString());
    }
    
    private void startNewQuiz() {
        Quiz_menu quizMenu = new Quiz_menu(userId, this);
        quizMenu.setVisible(true);
        this.setVisible(false);
    }
    
    private void showFullHistory() {
        List<scoreDAO.QuizResult> history = scoreDao.getUserHistory(userId);
        
        if (history.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "No quiz history found.\nTake some quizzes first!",
                "No History", 
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        StringBuilder historyText = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        
        historyText.append("FULL QUIZ HISTORY\n");
        historyText.append("=================\n\n");
        
        for (scoreDAO.QuizResult result : history) {
            historyText.append(String.format("Subject: %s\n", result.getSubject()));
            historyText.append(String.format("Score: %d/%d (%.1f%%)\n", 
                result.getScore(), result.getTotal(), result.getPercentage()));
            historyText.append(String.format("Time: %d seconds\n", result.getTimeTaken()));
            historyText.append(String.format("Date: %s\n", sdf.format(result.getCompletedAt())));
            historyText.append("-----------------\n");
        }
        
        JTextArea fullHistoryArea = new JTextArea(historyText.toString());
        fullHistoryArea.setEditable(false);
        fullHistoryArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        JScrollPane scrollPane = new JScrollPane(fullHistoryArea);
        scrollPane.setPreferredSize(new Dimension(500, 400));
        
        JOptionPane.showMessageDialog(this, scrollPane, 
            "Full Quiz History", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to logout?",
            "Confirm Logout",
            JOptionPane.YES_NO_OPTION);
            
        if (confirm == JOptionPane.YES_OPTION) {
            login loginScreen = new login();
            loginScreen.setVisible(true);
            this.dispose();
        }
    }
    public void refreshDashboard() {
    
        // Force reload data
        loadUserData();
        JOptionPane.showMessageDialog(this, 
        "Dashboard refreshed!\nStatistics updated.", 
        "Refreshed", 
        JOptionPane.INFORMATION_MESSAGE);
}
}