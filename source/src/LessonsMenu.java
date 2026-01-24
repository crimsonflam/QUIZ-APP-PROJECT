
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LessonsMenu extends JFrame {
    private int userId;
    private dashboard parentDashboard;
    
    public LessonsMenu(int userId, dashboard parentDashboard) {
        this.userId = userId;
        this.parentDashboard = parentDashboard;
        
        setupWindow();
        initComponents();
        setupLayout();
        setupListeners();
    }
    
    private void setupWindow() {
        setTitle("Quiz Master - Learning Center");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);  // We'll handle closing ourselves
        setSize(800, 650);
        setLocationRelativeTo(parentDashboard);
        setResizable(false);
        
        // Handle window closing properly
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                returnToDashboard();
            }
        });
    }
    
    private void initComponents() {
        // Components will be created in setupLayout
    }
    
    private void setupLayout() {
        // Main panel with background
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(240, 245, 255));
        
        // ========== HEADER ==========
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(70, 130, 180));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        headerPanel.setPreferredSize(new Dimension(getWidth(), 120));
        
        // Title
        JLabel titleLabel = new JLabel(" LEARNING CENTER");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        
        // Welcome message
        JLabel welcomeLabel = new JLabel("Choose a subject to start learning:");
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        welcomeLabel.setForeground(new Color(220, 220, 255));
        
        // Back button in header
        JButton headerBackBtn = createStyledButton(" Dashboard", new Color(100, 100, 150));
        headerBackBtn.addActionListener(e -> returnToDashboard());
        
        headerPanel.add(titleLabel, BorderLayout.NORTH);
        headerPanel.add(welcomeLabel, BorderLayout.CENTER);
        headerPanel.add(headerBackBtn, BorderLayout.EAST);
        
        // ========== LESSON CARDS ==========
        JPanel lessonsPanel = new JPanel(new GridLayout(2, 3, 20, 20));
        lessonsPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        lessonsPanel.setBackground(new Color(240, 245, 255));
        
        // Define all lessons
        String[] lessonTitles = {
            "Mathematics", "Science", "History", 
            "Geography", "Computer", "English"
        };
        
        String[] lessonIcons = {"MATH", "SC", "HS", "GEO", "CMP", "ENG"};
        
        Color[] lessonColors = {
            new Color(65, 105, 225),   // Royal Blue - Math
            new Color(50, 205, 50),    // Lime Green - Science
            new Color(218, 165, 32),   // Goldenrod - History
            new Color(30, 144, 255),   // Dodger Blue - Geography
            new Color(138, 43, 226),   // Blue Violet - Computer
            new Color(178, 34, 34)     // Firebrick - English
        };
        
        String[] lessonDescriptions = {
            "Algebra, Geometry, Calculus",
            "Physics, Chemistry, Biology",
            "Ancient to Modern History",
            "Countries, Maps, Cultures",
            "Java, Algorithms, Logic",
            "Grammar, Vocabulary, Writing"
        };
        
        // Create lesson cards
        for (int i = 0; i < lessonTitles.length; i++) {
            JPanel lessonCard = createLessonCard(
                lessonTitles[i],
                lessonDescriptions[i],
                lessonIcons[i],
                lessonColors[i]
            );
            lessonsPanel.add(lessonCard);
        }
        
        // ========== FOOTER ==========
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setBackground(new Color(240, 245, 255));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
        
        JLabel footerLabel = new JLabel("Complete lessons to improve your scores!");
        footerLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        footerLabel.setForeground(Color.GRAY);
        
        footerPanel.add(footerLabel);
        
        // ========== ASSEMBLE EVERYTHING ==========
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(lessonsPanel), BorderLayout.CENTER);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);
        
        setContentPane(mainPanel);
    }
    
    private JPanel createLessonCard(String title, String description, String icon, Color color) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(color);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(color.darker(), 2),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        // Add hover effect
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                card.setBackground(color.brighter());
                card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.WHITE, 2),
                    BorderFactory.createEmptyBorder(20, 20, 20, 20)
                ));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                card.setBackground(color);
                card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(color.darker(), 2),
                    BorderFactory.createEmptyBorder(20, 20, 20, 20)
                ));
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
                openLesson(title);
            }
        });
        
        // Icon
        JLabel iconLabel = new JLabel(icon, SwingConstants.CENTER);
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 40));
        iconLabel.setForeground(Color.WHITE);
        iconLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        
        // Title
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        
        // Description
        JLabel descLabel = new JLabel("<html><center>" + description + "</center></html>");
        descLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        descLabel.setForeground(new Color(240, 240, 240));
        descLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        // // Progress indicator (optional - shows if lesson is completed)
        // JProgressBar progressBar = new JProgressBar(0, 100);
        // progressBar.setValue(0);  // Start at 0% - you could load actual progress here
        // progressBar.setForeground(Color.WHITE);
        // progressBar.setBackground(color.darker());
        // progressBar.setBorderPainted(false);
        // progressBar.setString("Not Started");
        // progressBar.setStringPainted(true);
        // progressBar.setFont(new Font("Arial", Font.PLAIN, 10));
        // progressBar.setPreferredSize(new Dimension(0, 15));
        
        // Add everything to card
        JPanel centerPanel = new JPanel(new BorderLayout(5, 5));
        centerPanel.setOpaque(false);
        centerPanel.add(titleLabel, BorderLayout.NORTH);
        centerPanel.add(descLabel, BorderLayout.CENTER);
        // centerPanel.add(progressBar, BorderLayout.SOUTH);
        
        card.add(iconLabel, BorderLayout.NORTH);
        card.add(centerPanel, BorderLayout.CENTER);
        
        return card;
    }
    
    private void openLesson(String lessonTitle) {
        // Hide this menu
        this.setVisible(false);
        
        // Open the specific lesson
        switch (lessonTitle) {
            case "Mathematics":
                MathLesson mathLesson = new MathLesson(this, userId,parentDashboard);
                mathLesson.setVisible(true);
                break;
            case "Science":
                ScienceLesson scienceLesson = new ScienceLesson(this, userId);
                scienceLesson.setVisible(true);
                break;
            case "History":
                HistoryLesson historyLesson = new HistoryLesson(this, userId);
                historyLesson.setVisible(true);
                break;
            case "Geography":
                GeographyLesson geographyLesson = new GeographyLesson(this, userId);
                geographyLesson.setVisible(true);
                break;
            case "Computer":
                ComputerLesson computerLesson = new ComputerLesson(this, userId);
                computerLesson.setVisible(true);
                break;
            case "English":
                EnglishLesson englishLesson = new EnglishLesson(this, userId);
                englishLesson.setVisible(true);
                break;
        }
    }
    
    private void showComingSoonMessage(String lessonTitle) {
        int choice = JOptionPane.showOptionDialog(this,
            "<html><div style='text-align: center;'>" +
            "<b>" + lessonTitle + " Lessons</b><br><br>" +
            "This lesson module is coming soon!<br>" +
            "Would you like to:<br><br>" +
            "</div></html>",
            "Coming Soon",
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            new String[]{"Try Math Instead", "Return to Lessons", "Back to Dashboard"},
            "Return to Lessons");
        
        if (choice == 0) {  // Try Math Instead
            openLesson("Mathematics");
        } else if (choice == 1) {  // Return to Lessons
            // Stay here (window is already visible)
        } else if (choice == 2) {  // Back to Dashboard
            returnToDashboard();
        }
    }
    
    public void returnToLessonsMenu() {
        // This will be called when a lesson closes
        this.setVisible(true);
        this.toFront();
    }
    
    private void returnToDashboard() {
        // Show dashboard and close this window
        if (parentDashboard != null) {
            parentDashboard.returnFromLessons();
        }
        this.dispose();
    }
    
    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(color);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
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
        
        return button;
    }
    
    private void setupListeners() {
        // Additional listeners if needed
    }
}