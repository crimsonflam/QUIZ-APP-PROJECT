import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.HashMap;
import java.util.Map;

public class Quiz_menu {
    private static Map<String, Quiz_Subject> subjects = new HashMap<>();
    
    public static void main(String[] args) {
        // Initialize subjects (will connect later)
        subjects.put("MATHEMATICS", new Math_quiz());
        subjects.put("SCIENCE", new Science_Quiz());
        subjects.put("HISTORY", new History_Quiz());
        subjects.put("GEOGRAPHY", new Geography_Quiz());
        subjects.put("ENGLISH", new English_Quiz());
        subjects.put("COMPUTER", new Computer_Quiz());

        JFrame frame = new JFrame("Quiz Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.setLocationRelativeTo(null);
        
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
        
        // Subtitle
        JLabel subTitle = new JLabel("Choose Your Challenge");
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
            
            // Click handler
            subjectCard.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    startSubjectQuiz(config.name, frame);
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                    //animateCardEnter(subjectCard, config);
                    subjectCard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    //animateCardExit(subjectCard, config);
                    subjectCard.setCursor(Cursor.getDefaultCursor());
                }
            });
            
            gridPanel.add(subjectCard);
        }
        
        // ===== FOOTER =====
        JPanel footerPanel = new JPanel();
        footerPanel.setOpaque(false);
        footerPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0));
        
        JButton exitButton = createStyledExitButton();
        exitButton.addActionListener(e -> exitApplication(frame));
        
        footerPanel.add(exitButton);
        
        // ===== ASSEMBLE =====
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(gridPanel, BorderLayout.CENTER);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);
        
        frame.add(mainPanel);
        frame.setVisible(true);
    }
    
    // Configuration class for subjects
    static class SubjectConfig {
        String name;
        //String icon;
        String description;
        Color primaryColor;
        Color darkColor;
        
        SubjectConfig(String name /*, String icon*/, String description, 
                     Color primaryColor, Color darkColor) {
            this.name = name;
            //this.icon = icon;
            this.description = description;
            this.primaryColor = primaryColor;
            this.darkColor = darkColor;
        }
    }
    
    // Create a styled subject card
// Method to create a subject card - MATCHES ORIGINAL STYLE
private static JPanel createStyledSubjectCard(SubjectConfig config) {
    JPanel card = new JPanel(new BorderLayout());
    card.setBackground(Color.WHITE);
    card.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(config.primaryColor.darker(), 2),
        BorderFactory.createEmptyBorder(20, 20, 20, 20)
    ));
    card.setPreferredSize(new Dimension(250, 200));
    
    // Make card clickable with hover effects - EXACT SAME AS ORIGINAL
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
    
    // Title - ORIGINAL STYLE
    JLabel titleLabel = new JLabel(config.name, SwingConstants.CENTER);
    titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
    titleLabel.setForeground(config.primaryColor);
    titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
    
    // Description - ORIGINAL STYLE
    JTextArea descArea = new JTextArea(config.description);
    descArea.setFont(new Font("Arial", Font.PLAIN, 14));
    descArea.setForeground(new Color(80, 80, 80));
    descArea.setLineWrap(true);
    descArea.setWrapStyleWord(true);
    descArea.setEditable(false);
    descArea.setOpaque(false);
    descArea.setFocusable(false);
    
    // Icon (simple circle with first letter) - EXACT COPY FROM ORIGINAL
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
            
            // Draw first letter (not emoji) - ORIGINAL STYLE
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.BOLD, 24));
            FontMetrics fm = g2d.getFontMetrics();
            String firstLetter = config.name.substring(0, 1);  // First letter, not emoji
            int textWidth = fm.stringWidth(firstLetter);
            int textHeight = fm.getHeight();
            g2d.drawString(firstLetter, 
                (getWidth() - textWidth) / 2,
                (getHeight() + textHeight) / 2 - fm.getDescent());
        }
    };
    iconPanel.setPreferredSize(new Dimension(60, 60));  // Original size
    iconPanel.setOpaque(false);
    
    // Add components to card - ORIGINAL LAYOUT
    JPanel contentPanel = new JPanel(new BorderLayout());
    contentPanel.setOpaque(false);
    contentPanel.add(titleLabel, BorderLayout.NORTH);
    contentPanel.add(descArea, BorderLayout.CENTER);
    
    card.add(iconPanel, BorderLayout.NORTH);
    card.add(contentPanel, BorderLayout.CENTER);
    
    return card;
}    
    // Animation for mouse enter
    /*
    private static void animateCardEnter(JPanel card, SubjectConfig config) {
        // Scale up slightly
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(config.primaryColor, 3),
            BorderFactory.createEmptyBorder(23, 23, 23, 23)
        ));
        
        // Add shadow
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 0, 0, 30), 1),
            card.getBorder()
        ));
    }*/
    
    // Animation for mouse exit
    /*
    private static void animateCardExit(JPanel card, SubjectConfig config) {
        // Reset to normal
        card.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
    }
    */
    // Create styled exit button
    private static JButton createStyledExitButton() {
        JButton button = new JButton("EXIT APPLICATION") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                                   RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Draw button background
                Color baseColor = new Color(220, 20, 60);
                Color currentColor = getModel().isPressed() ? baseColor.darker() :
                                   getModel().isRollover() ? baseColor.brighter() : baseColor;
                
                GradientPaint gradient = new GradientPaint(
                    0, 0, currentColor.brighter(),
                    0, getHeight(), currentColor.darker()
                );
                g2d.setPaint(gradient);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
                
                // Draw button border
                g2d.setColor(new Color(139, 0, 0));
                g2d.setStroke(new BasicStroke(2));
                g2d.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, 25, 25);
                
                // Draw text
                g2d.setColor(Color.WHITE);
                g2d.setFont(getFont());
                FontMetrics fm = g2d.getFontMetrics();
                String text = getText();
                int textWidth = fm.stringWidth(text);
                int textHeight = fm.getHeight();
                g2d.drawString(text, 
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
    
    // Start subject quiz (temporary)
    private static void startSubjectQuiz(String subjectName, JFrame parent) {
        /*
        Object[] options = {"Start Quiz", "Cancel"};
        int choice = JOptionPane.showOptionDialog(parent,
            "  " + subjectName + "\n\n" +
            "Ready to test your knowledge?\n" +
            "This quiz contains 10 questions.",
            "Start Quiz",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]);
        
        if (choice == 0) {
            JOptionPane.showMessageDialog(parent,
                "Starting " + subjectName + " Quiz...\n\n" +
                "(Actual quiz implementation coming soon!)",
                "Quiz Loading",
                JOptionPane.INFORMATION_MESSAGE);
        }
        */
        Quiz_Subject subject = subjects.get(subjectName);
        if (subject != null) {
            subject.startQuiz(parent);
        } else {
            JOptionPane.showMessageDialog(parent,
                subjectName + " quiz is not available yet.",
                "Coming Soon",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    // Get subject index for emoji
    private static int getSubjectIndex(String subject) {
        switch(subject) {
            case "MATHEMATICS": return 0;
            case "SCIENCE": return 1;
            case "HISTORY": return 2;
            case "GEOGRAPHY": return 3;
            case "ENGLISH": return 4;
            case "COMPUTER": return 5;
            default: return 0;
        }
    }
    
    // Exit application
    private static void exitApplication(JFrame frame) {
        Object[] options = {"Yes, Exit", "No, Stay"};
        int choice = JOptionPane.showOptionDialog(frame,
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