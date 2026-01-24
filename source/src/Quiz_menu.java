

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.HashMap;
import java.util.Map;

public class Quiz_menu extends JFrame {
    private static Map<String, Quiz_Subject> subjects = new HashMap<>();
    private int userId; // 0 for guest, >0 for logged in user
    public JFrame parentFrame; // To go back to dashboard
    private dashboard dashboardRef;
    
    public Quiz_menu(int userId, JFrame parentFrame, dashboard dashboardRef) {
        this.userId = userId;
        this.parentFrame = parentFrame;
        this.dashboardRef = dashboardRef;
        initializeSubjects();
        initUI();
    }
    
    private void initializeSubjects() {
        // Initialize subjects
        subjects.put("MATHEMATICS", new Math_quiz());
        subjects.put("SCIENCE", new Science_Quiz());
        subjects.put("HISTORY", new History_Quiz());
        subjects.put("GEOGRAPHY", new Geography_Quiz());
        subjects.put("ENGLISH", new English_Quiz());
        subjects.put("COMPUTER", new Computer_Quiz());
    }
    
    private void initUI() {
        setTitle("Quiz Menu");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Changed from EXIT_ON_CLOSE
        setSize(1000, 800);
        setLocationRelativeTo(null);
        
        // Create gradient background panel
        JPanel mainPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                
                // Create vertical gradient
                GradientPaint gradient = new GradientPaint(
                    0, 0, new Color(240, 248, 255),  // Light blue at top
                    0, getHeight(), new Color(220, 240, 255)  // Slightly darker at bottom
                );
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        // ===== HEADER =====
        JPanel headerPanel = new JPanel();
        headerPanel.setOpaque(false);
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 40, 0));
        
        // Main title with shadow effect
        JLabel mainTitle = new JLabel("QUIZ Menu");
        mainTitle.setFont(new Font("Segoe UI", Font.BOLD, 56));
        mainTitle.setForeground(new Color(25, 25, 112)); // Midnight blue
        mainTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Add subtle shadow
        mainTitle.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(2, 2, 0, 0),
            BorderFactory.createLineBorder(new Color(25, 25, 112, 50), 0)
        ));
        
        // Subtitle - Show user status
        String userStatus = (userId > 0) ? "Logged In User" : "Guest Mode";
        JLabel subTitle = new JLabel("Choose Your Challenge - " + userStatus);
        subTitle.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        subTitle.setForeground(new Color(70, 70, 70));
        subTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        headerPanel.add(mainTitle);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        headerPanel.add(subTitle);
        
        // ===== SUBJECTS GRID =====
        JPanel gridPanel = new JPanel(new GridLayout(2, 3, 40, 40));
        gridPanel.setOpaque(false);
        gridPanel.setBorder(BorderFactory.createEmptyBorder(20, 60, 40, 60));
        
        // Subject configuration
        SubjectConfig[] subjectsConfig = {
            new SubjectConfig("MATHEMATICS", "Algebra, Geometry, Calculus", 
                            new Color(220, 20, 60), new Color(178, 34, 34)),
            new SubjectConfig("SCIENCE", "Physics, Chemistry, Biology", 
                            new Color(30, 144, 255), new Color(0, 0, 139)),
            new SubjectConfig("HISTORY", "Ancient, Medieval, Modern", 
                            new Color(34, 139, 34), new Color(0, 100, 0)),
            new SubjectConfig("GEOGRAPHY", "Countries, Maps, Capitals", 
                            new Color(255, 140, 0), new Color(184, 134, 11)),
            new SubjectConfig("ENGLISH", "Grammar, Literature, Writing", 
                            new Color(138, 43, 226), new Color(75, 0, 130)),
            new SubjectConfig("COMPUTER", "Programming, Hardware, Software", 
                            new Color(0, 191, 255), new Color(0, 139, 139))
        };
        
        // Create subject cards
        for (SubjectConfig config : subjectsConfig) {
            JPanel subjectCard = createStyledSubjectCard(config);
            
            // Click handler - FIXED: Removed frame parameter
            subjectCard.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    startSubjectQuiz(config.name); // Changed: removed frame parameter
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                    subjectCard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    subjectCard.setCursor(Cursor.getDefaultCursor());
                }
            });
            
            gridPanel.add(subjectCard);
        }
        
        // ===== FOOTER =====
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        footerPanel.setOpaque(false);
        footerPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0));
        
        // Back button for logged in users, Exit for guests
        if (userId > 0 ) {
            JButton backButton = createStyledButton("BACK TO DASHBOARD", 
                new Color(30, 144, 255), new Color(0, 0, 139));
            backButton.addActionListener(e -> goBackToDashboard());
            footerPanel.add(backButton);
        }
        
        JButton exitButton = createStyledButton("EXIT APPLICATION", 
            new Color(220, 20, 60), new Color(178, 34, 34));
        exitButton.addActionListener(e -> exitApplication());
        
        footerPanel.add(exitButton);
        
        // ===== ASSEMBLE =====
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(gridPanel, BorderLayout.CENTER);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    // Configuration class for subjects
    static class SubjectConfig {
        String name;
        String description;
        Color primaryColor;
        Color darkColor;
        
        SubjectConfig(String name, String description, 
                     Color primaryColor, Color darkColor) {
            this.name = name;
            this.description = description;
            this.primaryColor = primaryColor;
            this.darkColor = darkColor;
        }
    }
    
    // Create a styled subject card
    private JPanel createStyledSubjectCard(SubjectConfig config) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(config.primaryColor.darker(), 2),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        card.setPreferredSize(new Dimension(250, 200));
        
        // Make card clickable with hover effects
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                card.setCursor(new Cursor(Cursor.HAND_CURSOR));
                card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(config.primaryColor, 3),
                    BorderFactory.createEmptyBorder(20, 20, 20, 20)
                ));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                card.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(config.primaryColor.darker(), 2),
                    BorderFactory.createEmptyBorder(20, 20, 20, 20)
                ));
            }
        });
        
        // Title
        JLabel titleLabel = new JLabel(config.name, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(config.primaryColor);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        
        // Description
        JTextPane descPane = new JTextPane();
        descPane.setContentType("text/html");
        descPane.setText("<html><div style='text-align:center; font-family:Arial; font-size:14pt; color:#505050; font-weight:bold;'>" + config.description + "</div></html>");
        descPane.setEditable(false);
        descPane.setOpaque(false);
        descPane.setFocusable(false);
        
        // Icon (simple circle with first letter)
        JPanel iconPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                                   RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Draw circle - SOLID COLOR (no gradient)
                int diameter = Math.min(getWidth(), getHeight()) - 10;
                int x = (getWidth() - diameter) / 2;
                int y = (getHeight() - diameter) / 2;
                
                g2d.setColor(config.primaryColor);  // Solid color like original
                g2d.fillOval(x, y, diameter, diameter);
                
                // Draw first letter (not emoji)
                g2d.setColor(Color.WHITE);
                g2d.setFont(new Font("Arial", Font.BOLD, 24));
                FontMetrics fm = g2d.getFontMetrics();
                String firstLetter = config.name.substring(0, 1);
                int textWidth = fm.stringWidth(firstLetter);
                int textHeight = fm.getHeight();
                g2d.drawString(firstLetter, 
                    (getWidth() - textWidth) / 2,
                    (getHeight() + textHeight) / 2 - fm.getDescent());
            }
        };
        iconPanel.setPreferredSize(new Dimension(60, 60));
        iconPanel.setOpaque(false);
        
        // Add components to card
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setOpaque(false);
        contentPanel.add(titleLabel, BorderLayout.NORTH);
        contentPanel.add(descPane, BorderLayout.CENTER);
        
        card.add(iconPanel, BorderLayout.NORTH);
        card.add(descPane, BorderLayout.CENTER);
        
        return card;
    }
    
    // Create styled button (reusable)
    private JButton createStyledButton(String text, Color baseColor, Color darkColor) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                                   RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Draw button background
                Color currentColor = getModel().isPressed() ? darkColor :
                                   getModel().isRollover() ? baseColor.brighter() : baseColor;
                
                GradientPaint gradient = new GradientPaint(
                    0, 0, currentColor.brighter(),
                    0, getHeight(), currentColor.darker()
                );
                g2d.setPaint(gradient);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
                
                // Draw button border
                g2d.setColor(darkColor);
                g2d.setStroke(new BasicStroke(2));
                g2d.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, 25, 25);
                
                // Draw text
                g2d.setColor(Color.WHITE);
                g2d.setFont(getFont());
                FontMetrics fm = g2d.getFontMetrics();
                String buttonText = getText();
                int textWidth = fm.stringWidth(buttonText);
                int textHeight = fm.getHeight();
                g2d.drawString(buttonText, 
                    (getWidth() - textWidth) / 2,
                    (getHeight() + textHeight) / 2 - fm.getDescent());
            }
            
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(250, 55);
            }
            
            @Override
            public boolean contains(int x, int y) {
                // Make button clickable only in rounded area
                int radius = getHeight() / 2;
                if (x < radius) {
                    // Left side - check if in circle
                    int centerY = getHeight() / 2;
                    return Math.pow(x - radius, 2) + Math.pow(y - centerY, 2) <= radius * radius;
                } else if (x > getWidth() - radius) {
                    // Right side - check if in circle
                    int centerY = getHeight() / 2;
                    return Math.pow(x - (getWidth() - radius), 2) + Math.pow(y - centerY, 2) <= radius * radius;
                }
                // Middle part is always clickable
                return true;
            }
        };
        
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        
        // Hover cursor
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                button.setCursor(Cursor.getDefaultCursor());
            }
        });
        
        return button;
    }
    
    // Start subject quiz - FIXED: Removed JFrame parameter
    private void startSubjectQuiz(String subjectName) {
        Quiz_Subject subject = subjects.get(subjectName);
        if (subject != null) {
            subject.startQuiz(this, userId); // Pass userId to quiz
        } else {
            JOptionPane.showMessageDialog(this,
                subjectName + " quiz is not available yet.",
                "Coming Soon",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    // Go back to dashboard
    private void goBackToDashboard() {
        dashboardRef.refreshDashboard();  // Refresh before showing
        dashboardRef.setVisible(true);
        SwingUtilities.invokeLater(() -> this.dispose());
    // if (parentFrame != null && parentFrame instanceof dashboard) {
    //     dashboard dash = (dashboard) parentFrame ;
    //     dash.refreshDashboard();  // Refresh before showing
    //     parentFrame.setVisible(true);
    //     SwingUtilities.invokeLater(() -> this.dispose());
    // }
    //     this.dispose();
     }
    
    // Exit application
    private void exitApplication() {
        Object[] options = {"Yes, Exit", "No, Stay"};
        int choice = JOptionPane.showOptionDialog(this,
            "Are you sure you want to exit Quiz Menu?",
            "Exit Confirmation",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[1]);
        
        if (choice == 0) {
            System.exit(0);
        }
    }
}