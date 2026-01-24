import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GeographyLesson extends JFrame {
    private LessonsMenu parentMenu;
    private int currentPage = 1;
    private int totalPages = 3;
    private int userId;
    private dashboard dashboardRef;



    public GeographyLesson(LessonsMenu parentMenu, int userId) {
        this.parentMenu = parentMenu;
        this.userId = userId;
        this.dashboardRef = dashboardRef;
    
        setupWindow();
        setupLayout();
    }
    
    private void setupWindow() {
        setTitle("Geography Lesson ");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(1000, 650);
        setLocationRelativeTo(parentMenu);
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                returnToLessonsMenu();
            }
        });
    }
    
    private void setupLayout() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        
        // ========== HEADER ==========
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(65, 105, 225));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        
        JLabel titleLabel = new JLabel(" GEOGRAPHY LESSON ");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        
        JLabel pageLabel = new JLabel("Page " + currentPage + "/" + totalPages);
        pageLabel.setFont(new Font("Arial", Font.BOLD, 14));
        pageLabel.setForeground(Color.WHITE);
        
        JButton backButton = createStyledButton(" Back to Lessons", new Color(100, 100, 150));
        backButton.addActionListener(e -> returnToLessonsMenu());
        
        headerPanel.add(titleLabel, BorderLayout.WEST);
        headerPanel.add(pageLabel, BorderLayout.CENTER);
        headerPanel.add(backButton, BorderLayout.EAST);
        
        // ========== CONTENT ==========
        JPanel contentPanel = createPageContent(currentPage);
        
        // ========== NAVIGATION ==========
        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        navPanel.setBackground(Color.WHITE);
        
        JButton prevButton = createNavButton(" Previous", new Color(50, 70, 111));
        JButton nextButton = createNavButton("Next ", new Color(65, 105, 225));
        //JButton quizButton = createNavButton("Take Quiz", new Color(50, 150, 50));
          
            //the "url" version of django
        prevButton.addActionListener(e -> showPreviousPage(contentPanel, pageLabel));
        nextButton.addActionListener(e -> showNextPage(contentPanel, pageLabel));
        //quizButton.addActionListener(e -> startNewQuiz());
        
        // Disable buttons based on page
        updateNavButtons(prevButton, nextButton);
        
        navPanel.add(prevButton);
        navPanel.add(nextButton);
        //navPanel.add(quizButton);
        
        // ========== ASSEMBLE ==========
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(contentPanel), BorderLayout.CENTER);
        mainPanel.add(navPanel, BorderLayout.SOUTH);
        
        // Adjust scroll speed
        JScrollBar vertical = ((JScrollPane) mainPanel.getComponent(1)).getVerticalScrollBar();
        vertical.setUnitIncrement(16);
        vertical.setBlockIncrement(50);
        setContentPane(mainPanel);
    }
    
    private JPanel createPageContent(int page) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        panel.setBackground(Color.WHITE);
        
        LessonQuestion[] level1 = {
            new LessonQuestion(
        "What is the capital city of France?",
        new String[]{"Paris", "Berlin", "Rome", "Madrid"},
        0
    ),

    new LessonQuestion(
        "Which ocean is the largest in the world?",
        new String[]{"Pacific Ocean", "Atlantic Ocean", "Indian Ocean", "Arctic Ocean"},
        0
    ),

    new LessonQuestion(
        "Which continent is the largest by land area?",
        new String[]{"Asia", "Africa", "Europe", "Australia"},
        0
    )
};
        LessonQuestion[] level2 = { new LessonQuestion(
        "What is the capital of Mongolia?",
        new String[]{"Ulaanbaatar", "Karakorum", "Erdenet", "Darkhan"},
        0
    ),

    new LessonQuestion(
        "Which river flows through the city of Baghdad?",
        new String[]{"Tigris River", "Euphrates River", "Nile River", "Jordan River"},
        0
    ),

    new LessonQuestion(
        "Which country has the longest coastline in the world?",
        new String[]{"Canada", "Russia", "Indonesia", "Norway"},
        0
    ),};
        LessonQuestion[] level3 = {
        new LessonQuestion(
        "Which mountain range traditionally separates Europe from Asia?",
        new String[]{"Ural Mountains", "Alps", "Caucasus Mountains", "Carpathian Mountains"},
        0
    ),

    new LessonQuestion(
        "Which sea has the highest salinity in the world?",
        new String[]{"Dead Sea", "Red Sea", "Mediterranean Sea", "Caspian Sea"},
        0
    ),

    new LessonQuestion(
        "Which country has the highest number of UNESCO World Heritage Sites?",
        new String[]{"Italy", "China", "Spain", "France"},
        0
    ),};

        switch(page) {
                case 1: // Easy Level
    addLessonTitle(panel, "Lesson 1: Introduction to World Geography");

    addText(panel,
        "Geography helps us understand the Earth and how humans interact with it. " +
        "In this lesson, we explore continents, oceans, rivers, mountains, and capital cities. " +
        "These basic facts form the foundation for understanding cultures, climate, trade, and global connections.");
    addSpace(panel, 20);

    // ===== Capitals of the World =====
    addSubtitle(panel, "1. Capital Cities");
    addText(panel,
        "Capital cities are important because they usually serve as the political and administrative centers of a country. " +
        "They often contain government buildings, historical landmarks, and cultural institutions.");
    addSpace(panel, 10);

    addBullet(panel, "Paris is the capital of France and is known for its culture, art, and history.");
    addBullet(panel, "Canberra is the capital of Australia, chosen as a compromise between Sydney and Melbourne.");
    addBullet(panel, "Ottawa serves as the capital of Canada and is home to the national government.");
    addBullet(panel, "New Delhi is the capital of India and plays a major role in politics and diplomacy.");
    addSpace(panel, 10);

    addExample(panel, "Example:", "The capital of France is Paris.");
    addSpace(panel, 20);

    // ===== Oceans, Rivers, and Seas =====
    addSubtitle(panel, "2. Oceans, Rivers, and Seas");
    addText(panel,
        "Large bodies of water play a major role in shaping Earth’s climate and supporting life. " +
        "Oceans regulate temperature, while rivers provide fresh water and support civilizations.");
    addSpace(panel, 10);

    addBullet(panel, "The Pacific Ocean is the largest and deepest ocean on Earth.");
    addBullet(panel, "The Nile River is often considered the longest river in the world.");
    addBullet(panel, "The Mediterranean Sea has been vital to trade and civilization for thousands of years.");
    addSpace(panel, 10);

    addExample(panel, "Example:", "The largest ocean in the world is the Pacific Ocean.");
    addSpace(panel, 20);

    // ===== Continents and Landforms =====
    addSubtitle(panel, "3. Continents and Natural Landmarks");
    addText(panel,
        "Earth’s land is divided into continents, each with unique landscapes and cultures. " +
        "Mountains, deserts, and forests help define the physical features of each continent.");
    addSpace(panel, 10);

    addBullet(panel, "Asia is the largest continent by both size and population.");
    addBullet(panel, "Australia is the smallest continent.");
    addBullet(panel, "Mount Everest is the highest mountain in the world.");
    addBullet(panel, "The Sahara Desert is the largest hot desert on Earth.");
    addSpace(panel, 10);

    addExample(panel, "Example:", "Asia is the largest continent on Earth.");
    addSpace(panel, 20);

    addText(panel,
        "By completing this lesson, you now understand basic geographic facts about the world, " +
        "including capitals, oceans, continents, and major natural features.");
    

                    addSubtitle(panel, "Quick Test : Geography");
                    addText(panel, "Try these questions! Select an answer and click 'Check Answers' to see your score and corrections.");
                    addSpace(panel, 20);

                for (LessonQuestion q : level1) {
                        addLessonQuestion(panel, q);
                    }
                    addSpace(panel, 20);
                    JButton checkButton = new JButton("Check Answers");
                    checkButton.addActionListener(e -> {
                        int score = 0;
                        StringBuilder feedback = new StringBuilder();

                        for (LessonQuestion q : level1) {
                            if (q.isCorrect()) {
                                score++;
                                feedback.append(q.getQuestionText()).append(" -> Correct!\n");
                            } else {
                                feedback.append(q.getQuestionText())
                                        .append(" -> Incorrect. Correct answer: ")
                                        .append(q.getOptions()[q.getCorrectIndex()])
                                        .append("\n");
                            }
                        }

                        JOptionPane.showMessageDialog(panel,
                                "Your score: " + score + "/" + level1.length + "\n\n" + feedback.toString());
                    });
                    panel.add(checkButton);


                break;
       
            case 2: // Medium Level
    addLessonTitle(panel, "Lesson 2: Global Geography and Regional Features");

    addText(panel,
        "This lesson builds on basic geography by exploring lesser-known capitals, important rivers, " +
        "time zones, coastlines, deserts, and unique geographic boundaries. These details help explain " +
        "how geography influences politics, culture, and daily life.");
    addSpace(panel, 20);

    // ===== Capitals and Countries =====
    addSubtitle(panel, "1. Capitals Beyond the Basics");
    addText(panel,
        "Many countries have capitals that are not their largest cities. " +
        "These capitals often hold historical or political importance.");
    addSpace(panel, 10);

    addBullet(panel, "Ulaanbaatar is the capital of Mongolia and one of the coldest capitals in the world.");
    addBullet(panel, "Lima is the capital of Peru and lies along the Pacific coast.");
    addBullet(panel, "Thimphu is the capital of Bhutan and reflects traditional Himalayan culture.");
    addBullet(panel, "Tashkent serves as the capital of Uzbekistan and is a major Central Asian city.");
    addSpace(panel, 10);

    addExample(panel, "Example:", "The capital of Mongolia is Ulaanbaatar.");
    addSpace(panel, 20);

    // ===== Rivers, Seas, and Deserts =====
    addSubtitle(panel, "2. Rivers, Seas, and Deserts");
    addText(panel,
        "Geographic features such as rivers and deserts often determine where people live " +
        "and how civilizations develop.");
    addSpace(panel, 10);

    addBullet(panel, "The Tigris River flows through Baghdad and supports agriculture.");
    addBullet(panel, "The Atacama Desert in Chile is one of the driest places on Earth.");
    addBullet(panel, "The Caspian Sea is the largest landlocked body of water in the world.");
    addBullet(panel, "Victoria Falls is located on the Zambezi River in Africa.");
    addSpace(panel, 10);

    addExample(panel, "Example:", "The river that flows through Baghdad is the Tigris River.");
    addSpace(panel, 20);

    // ===== Unique Geographic Facts =====
    addSubtitle(panel, "3. Unique Geographic Characteristics");
    addText(panel,
        "Some countries are notable for unique geographic traits, such as coastlines, volcanoes, " +
        "or their position across continents.");
    addSpace(panel, 10);

    addBullet(panel, "Canada has the longest coastline in the world.");
    addBullet(panel, "Indonesia has the most volcanoes due to its position on tectonic plates.");
    addBullet(panel, "Russia spans both Europe and Asia.");
    addBullet(panel, "France has the most time zones because of its overseas territories.");
    addSpace(panel, 10);

    addExample(panel, "Example:", "The country with the longest coastline is Canada.");
    addSpace(panel, 20);

    addText(panel,
        "By completing this lesson, you now understand how geography varies across regions " +
        "and how physical features shape nations and societies.");

addSubtitle(panel, "Quick Test: Geography");
                    addText(panel, "Try these questions! Select an answer and click 'Check Answers' to see your score and corrections.");
                    addSpace(panel, 20);

                    
                    addSubtitle(panel, "Quick Test : Geography");
                    addText(panel, "Try these questions! Select an answer and click 'Check Answers' to see your score and corrections.");
                    addSpace(panel, 20);
                    JButton check_Button = new JButton("Check Answers");
                        for (LessonQuestion q : level2) {
                        addLessonQuestion(panel, q);
                    }
                    addSpace(panel, 20);
                    check_Button.addActionListener(e -> {
                        int score = 0;
                        StringBuilder feedback = new StringBuilder();
                        for (LessonQuestion q : level2) {
                            if (q.isCorrect()) {
                                score++;
                                feedback.append(q.getQuestionText()).append(" -> Correct!\n");
                            } else {
                                feedback.append(q.getQuestionText())
                                        .append(" -> Incorrect. Correct answer: ")
                                        .append(q.getOptions()[q.getCorrectIndex()])
                                        .append("\n");
                            }
                        }
                        JOptionPane.showMessageDialog(panel,
                                "Your score: " + score + "/" + level2.length + "\n\n" + feedback.toString());
                    });
                    panel.add(check_Button);
                    break;
        case 3: // Hard Level
     addLessonTitle(panel, "Lesson 3: Advanced World Geography");

    addText(panel,
        "This advanced lesson focuses on detailed geographic knowledge, including small nations, " +
        "political boundaries, heritage sites, and extreme natural conditions. " +
        "These topics require careful attention and global awareness.");
    addSpace(panel, 20);

    // ===== Small Nations and Capitals =====
    addSubtitle(panel, "1. Small Nations and Unique Capitals");
    addText(panel,
        "Some countries are very small in size or population but still play important roles " +
        "in global geography and politics.");
    addSpace(panel, 10);

    addBullet(panel, "Mbabane is the capital of Eswatini, formerly known as Swaziland.");
    addBullet(panel, "Yaren District serves as the de facto capital of Nauru.");
    addBullet(panel, "Vaduz is the capital of Liechtenstein.");
    addBullet(panel, "Bandar Seri Begawan is the capital of Brunei.");
    addSpace(panel, 10);

    addExample(panel, "Example:", "The capital of Liechtenstein is Vaduz.");
    addSpace(panel, 20);

    // ===== Borders and Physical Divisions =====
    addSubtitle(panel, "2. Borders, Mountains, and Rivers");
    addText(panel,
        "Natural features often form borders between countries and continents. " +
        "Mountains and rivers have historically influenced trade, defense, and migration.");
    addSpace(panel, 10);

    addBullet(panel, "The Rio Grande forms part of the border between the United States and Mexico.");
    addBullet(panel, "The Ural Mountains traditionally separate Europe from Asia.");
    addBullet(panel, "Borneo is an island shared by Indonesia and Malaysia.");
    addSpace(panel, 10);

    addExample(panel, "Example:", "The mountain range separating Europe and Asia is the Ural Mountains.");
    addSpace(panel, 20);

    // ===== Global Records and Extremes =====
    addSubtitle(panel, "3. Global Records and Extremes");
    addText(panel,
        "Some geographic facts stand out due to their extreme or record-breaking nature, " +
        "including salinity, forest coverage, and cultural heritage.");
    addSpace(panel, 10);

    addBullet(panel, "Italy has the highest number of UNESCO World Heritage Sites.");
    addBullet(panel, "The Dead Sea has the highest salinity of any sea.");
    addBullet(panel, "Suriname has one of the highest percentages of forest cover.");
    addBullet(panel, "Canada has the most freshwater lakes in the world.");
    addSpace(panel, 10);

    addExample(panel, "Example:", "The sea with the highest salinity is the Dead Sea.");
    addSpace(panel, 20);

    addText(panel,
        "By completing this lesson, you have developed advanced geographic knowledge " +
        "covering political boundaries, extreme environments, and global records.");
        addSubtitle(panel, "Quick Test: Geography");
                    addText(panel, "Try these questions! Select an answer and click 'Check Answers' to see your score and corrections.");
                    addSpace(panel, 20);

    addSpace(panel, 25);
                    addSubtitle(panel, "Quick Test : Geography");
                    addText(panel, "Try these questions! Select an answer and click 'Check Answers' to see your score and corrections.");
                    addSpace(panel, 20);
                    JButton checkbtn = new JButton("Check Answers");
                        for (LessonQuestion q : level3) {
                        addLessonQuestion(panel, q);
                    }
                    addSpace(panel, 20);
                    checkbtn.addActionListener(e -> {
                        int score = 0;
                        StringBuilder feedback = new StringBuilder();
                        for (LessonQuestion q : level3) {
                            if (q.isCorrect()) {
                                score++;
                                feedback.append(q.getQuestionText()).append(" -> Correct!\n");
                            } else {
                                feedback.append(q.getQuestionText())
                                        .append(" -> Incorrect. Correct answer: ")
                                        .append(q.getOptions()[q.getCorrectIndex()])
                                        .append("\n");
                            }
                        }
                        JOptionPane.showMessageDialog(panel,
                                "Your score: " + score + "/" + level3.length + "\n\n" + feedback.toString());
                    });
                    panel.add(checkbtn);
                    break;
        
        }
        
        return panel;
    }
    
    // Helper methods for creating content
    private void addLessonTitle(JPanel panel, String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setForeground(new Color(65, 105, 225));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(label);
    }
    
    private void addSubtitle(JPanel panel, String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(new Color(70, 70, 70));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(label);
    }
    
    private void addText(JPanel panel, String text) {
        JLabel label = new JLabel("<html><div style='width:650px; margin-bottom:10px;'>" + text + "</div></html>");
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(label);
    }
    
    private void addBullet(JPanel panel, String text) {
        JLabel label = new JLabel("\u2022 " + text);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(0, 20, 5, 0));
        panel.add(label);
    }
    
    private void addExample(JPanel panel, String title, String example) {
        JPanel examplePanel = new JPanel(new BorderLayout());
        examplePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        examplePanel.setBackground(new Color(240, 248, 255));
        examplePanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setForeground(new Color(178, 34, 34));
        
        JLabel exampleLabel = new JLabel(example);
        exampleLabel.setFont(new Font("Monospaced", Font.BOLD, 16));
        exampleLabel.setForeground(new Color(0, 100, 0));
        
        examplePanel.add(titleLabel, BorderLayout.WEST);
        examplePanel.add(exampleLabel, BorderLayout.EAST);
        panel.add(examplePanel);
        addSpace(panel, 10);
    }
    
    private void addSpace(JPanel panel, int height) {
        panel.add(Box.createRigidArea(new Dimension(0, height)));
    }

    private LessonQuestion[] concatArrays(LessonQuestion[]... arrays) {
    int totalLength = 0;
    for (LessonQuestion[] arr : arrays) totalLength += arr.length;
    LessonQuestion[] result = new LessonQuestion[totalLength];
    int pos = 0;
    for (LessonQuestion[] arr : arrays) {
        for (LessonQuestion q : arr) result[pos++] = q;
    }
    return result;
}

// Add a question to the panel in your lesson style
private void addLessonQuestion(JPanel panel, LessonQuestion q) {
    addText(panel, q.getQuestionText());  // Use your existing addText()

    ButtonGroup group = new ButtonGroup();
    JPanel optionsPanel = new JPanel();
    optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));

    for (int i = 0; i < q.getOptions().length; i++) {
        JRadioButton rb = new JRadioButton(q.getOptions()[i]);
        final int idx = i;
        rb.addActionListener(e -> q.setUserSelection(idx));
        group.add(rb);
        optionsPanel.add(rb);
    }

    panel.add(optionsPanel);
    addSpace(panel, 10);
}
    
    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setForeground(Color.WHITE);
        button.setBackground(color);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
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
    
    private JButton createNavButton(String text, Color color) {
        JButton button = createStyledButton(text, color);
        button.setFont(new Font("Arial", Font.BOLD, 13));
        button.setPreferredSize(new Dimension(120, 35));
        return button;
    }
    
    private void showPreviousPage(JPanel contentPanel, JLabel pageLabel) {
        if (currentPage > 1) {
            currentPage--;
            updateContent(contentPanel, pageLabel);
        }
    }
    
    private void showNextPage(JPanel contentPanel, JLabel pageLabel) {
        if (currentPage < totalPages) {
            currentPage++;
            updateContent(contentPanel, pageLabel);
        }
    }
    
    private void updateContent(JPanel contentPanel, JLabel pageLabel) {
        contentPanel.removeAll();
        JPanel newContent = createPageContent(currentPage);
        
        Component[] components = newContent.getComponents();
        for (Component comp : components) {
            contentPanel.add(comp);
        }
        
        pageLabel.setText("Page " + currentPage + "/" + totalPages);
        
        contentPanel.revalidate();
        contentPanel.repaint();
        
        // Update navigation buttons
        JPanel navPanel = (JPanel)((JPanel)getContentPane().getComponent(2));
        updateNavButtons((JButton)navPanel.getComponent(0), (JButton)navPanel.getComponent(1));
    }
    
    private void updateNavButtons(JButton prevButton, JButton nextButton) {
        prevButton.setEnabled(currentPage > 1);
        prevButton.setBackground(currentPage > 1 ? new Color(100, 100, 100) : Color.LIGHT_GRAY);
        
        nextButton.setEnabled(currentPage < totalPages);
        nextButton.setBackground(currentPage < totalPages ? new Color(65, 105, 225) : Color.LIGHT_GRAY);
    }
    
    private void startNewQuiz() {
        Quiz_menu quizMenu = new Quiz_menu(userId, this, dashboardRef);
        quizMenu.setVisible(true);
        this.setVisible(false);
    }
    
    private void returnToLessonsMenu() {
        if (parentMenu != null) {
            parentMenu.returnToLessonsMenu();
        }
        this.dispose();
    }
    
}