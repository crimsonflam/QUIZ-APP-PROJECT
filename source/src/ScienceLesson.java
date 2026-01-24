import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ScienceLesson extends JFrame {
    private LessonsMenu parentMenu;
    private int currentPage = 1;
    private int totalPages = 3;
    private int userId;
    private dashboard dashboardRef;


    public ScienceLesson(LessonsMenu parentMenu, int userId) {
        this.parentMenu = parentMenu;
        this.userId = userId;
        this.dashboardRef = dashboardRef;
    
        setupWindow();
        setupLayout();
    }
    
    private void setupWindow() {
        setTitle("Science Lesson ");
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
        
        JLabel titleLabel = new JLabel(" SCIENCE LESSON ");
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
            new LessonQuestion("What planet is known as the Red Planet?",new String[]{"Mars", "Venus", "Jupiter", "Saturn"}, 0),
            new LessonQuestion("What is H2O?",new String[]{"Water", "Hydrogen", "Oxygen", "Salt"}, 0),
            new LessonQuestion("What gas do humans breathe in?",new String[]{"Oxygen", "Carbon Dioxide", "Nitrogen", "Hydrogen"}, 0)
        };
        LessonQuestion[] level2 = {
            new LessonQuestion("What is the pH of pure water?",new String[]{"7", "0", "14", "1"}, 0),
            new LessonQuestion("Chemical symbol for sodium?",new String[]{"Na", "So", "Sd", "Sm"}, 0),
            new LessonQuestion("Process by which plants make food?",new String[]{"Photosynthesis", "Respiration", "Transpiration", "Fermentation"}, 0),
        };
        LessonQuestion[] level3 = {
            new LessonQuestion("Heisenberg Uncertainty Principle states:",
        new String[]{"Position and momentum cannot both be known precisely",
                     "Energy is continuous",
                     "Speed of light varies",
                     "Mass is conserved"}, 0),
    new LessonQuestion("CRISPR-Cas9 is used for:",
        new String[]{"Gene editing", "Protein synthesis", "DNA sequencing", "Cell division"}, 0),
    new LessonQuestion("What is dark energy?",
        new String[]{"Causes accelerated expansion of the universe",
                     "Holds galaxies together",
                     "Forms black holes",
                     "Is visible matter"}, 0),
        };

        switch(page) {
                case 1:  

                    // ================= PAGE 1 =================
                    addLessonTitle(panel, "Science: Understanding the Natural World");
                    addText(panel,
                        "Science is the study of the natural world through observation, experimentation, and reasoning. " +
                        "It helps us understand how the universe works, from tiny particles inside atoms to massive galaxies in space. " +
                        "In this lesson, we will explore basic, intermediate, and advanced scientific ideas that explain everyday " +
                        "phenomena as well as complex processes studied by scientists.");
                    addSpace(panel, 20);

                    addSubtitle(panel, "Basic Science and Our Everyday World");
                    addText(panel,
                        "Basic science focuses on ideas that describe the world around us and how living and non-living things behave. " +
                        "These concepts form the foundation of biology, chemistry, physics, and astronomy. Understanding them helps us " +
                        "explain simple observations such as why plants grow, why objects fall, and how our bodies function.");

                    addText(panel,
                        "Our solar system is made up of planets orbiting a central star. Mars is often called the Red Planet because " +
                        "its surface contains iron oxide, giving it a reddish appearance. The largest planet in our solar system is " +
                        "Jupiter, while the Sun is the closest star to Earth and the primary source of energy for life on our planet.");

                    addText(panel,
                        "Water is one of the most important substances for life. Its chemical formula is H2O, meaning it is made from " +
                        "two hydrogen atoms and one oxygen atom. Water boils at 100 degrees Celsius and has special properties that allow " +
                        "living organisms to survive.");

                    addText(panel,
                        "Air is a mixture of gases. Although humans breathe in oxygen, nitrogen is actually the most abundant gas in the " +
                        "atmosphere. Oxygen is essential for respiration, a process that releases energy inside our bodies.");

                    addExample(panel, "Example:",
                        "When humans breathe in oxygen, it is transported by blood to cells where it helps release energy from food.");
                    addSpace(panel, 30);
                    addSubtitle(panel, "Quick Test: Science");
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
       
        case 2:
                    addLessonTitle(panel, "Life, Matter, and Energy");
                    addText(panel,
                        "Living organisms have specialized systems that allow them to survive and function. Humans are mammals, " +
                        "and the human body is made up of many organs working together. The heart pumps blood throughout the body, " +
                        "delivering oxygen and nutrients to cells. An adult human body contains 206 bones, which provide structure " +
                        "and protect vital organs.");

                    addText(panel,
                        "Matter exists in different states, including solid, liquid, and gas. These states depend on how tightly " +
                        "particles are packed together. Fire is not a state of matter; instead, it is the result of a chemical reaction " +
                        "that releases energy in the form of heat and light.");

                    addText(panel,
                        "Plants use a process called photosynthesis to make their own food. During this process, plants absorb sunlight " +
                        "and carbon dioxide and release oxygen as a byproduct. This oxygen is crucial for most living organisms on Earth.");

                    addText(panel,
                        "In chemistry, elements are represented by symbols. For example, gold is represented by the symbol Au. " +
                        "When iron reacts with oxygen over time, it forms rust, which is chemically known as iron oxide.");

                    addText(panel,
                        "Physics explains forces and motion. Gravity is the force that pulls objects toward Earth. It is the reason why " +
                        "objects fall when dropped and why planets stay in orbit around stars.");

                    addExample(panel, "Example:",
                        "If you drop a ball from your hand, gravity pulls it downward until it reaches the ground.");
                    addSpace(panel, 30);
                    
                    addSubtitle(panel, "Quick Test: Science");
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
        case 3:
                    addLessonTitle(panel, "From Scientific Laws to Modern Science");
                    addText(panel,
                        "As science advances, we develop laws and theories that describe how nature behaves under different conditions. " +
                        "In physics, Newton's First Law, also known as the Law of Inertia, states that an object will remain at rest or " +
                        "move at a constant velocity unless acted upon by an external force.");

                    addText(panel,
                        "Electricity follows specific rules. The unit of electric current is the ampere, and Ohm's Law explains the " +
                        "relationship between voltage, current, and resistance using the equation V = I x R.");

                    addText(panel,
                        "In biology, cells divide through a process called mitosis, which results in two identical cells. DNA carries " +
                        "genetic information, and RNA plays a role in using that information to build proteins.");

                    addText(panel,
                        "Advanced science explores ideas that are not visible to the naked eye. Quantum physics introduces concepts like " +
                        "uncertainty and superposition, while modern genetics allows scientists to edit genes using tools such as CRISPR. " +
                        "In astronomy and cosmology, scientists study dark energy and relativity to understand how the universe expands " +
                        "and how gravity shapes space and time.");

                    addText(panel,
                        "Science is constantly evolving. New discoveries build upon existing knowledge, allowing us to better understand " +
                        "the universe and improve technology, medicine, and our quality of life.");

                    addExample(panel, "Final Thought:",
                        "Scientific knowledge grows by asking questions, testing ideas, and learning from evidence.");
                    addSpace(panel, 20);

                    addSubtitle(panel, "Quick Test: Science");
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
        // case 5:
        //             addLessonTitle(panel, "Science Knowledge Check");
        //             addText(panel,
        //                 "Answer the following questions to test your understanding of the science concepts covered in this lesson. " +
        //                 "Choose the best answer for each question, then click the button at the bottom to see your results.");
        //             addSpace(panel, 20);
        //                 LessonQuestion[] allQuestions = concatArrays(level1, level2, level3);

        //             for (LessonQuestion q : allQuestions) {
        //                 addLessonQuestion(panel, q);
        //             }
        //             addSpace(panel, 20);
        //             JButton checkButton = new JButton("Check Answers");
        //             checkButton.addActionListener(e -> {
        //                 int score = 0;
        //                 StringBuilder feedback = new StringBuilder();

        //                 for (LessonQuestion q : allQuestions) {
        //                     if (q.isCorrect()) {
        //                         score++;
        //                         feedback.append(q.getQuestionText()).append(" -> Correct!\n");
        //                     } else {
        //                         feedback.append(q.getQuestionText())
        //                                 .append(" -> Incorrect. Correct answer: ")
        //                                 .append(q.getOptions()[q.getCorrectIndex()])
        //                                 .append("\n");
        //                     }
        //                 }

        //                 JOptionPane.showMessageDialog(panel,
        //                         "Your score: " + score + "/" + allQuestions.length + "\n\n" + feedback.toString());
        //             });
        //             panel.add(checkButton);
        //             break;
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