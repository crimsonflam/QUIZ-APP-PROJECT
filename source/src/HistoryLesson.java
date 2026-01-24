import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HistoryLesson extends JFrame {
    private LessonsMenu parentMenu;
    private int currentPage = 1;
    private int totalPages = 3;
    private int userId;
    private dashboard dashboardRef;



    public HistoryLesson(LessonsMenu parentMenu, int userId) {
        this.parentMenu = parentMenu;
        this.userId = userId;
        this.dashboardRef = dashboardRef;
    
        setupWindow();
        setupLayout();
    }
    
    private void setupWindow() {
        setTitle("History Lesson ");
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
        
        JLabel titleLabel = new JLabel(" HISTORY LESSON ");
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
    "What were the rulers of Ancient Egypt called?",
    new String[]{
        "Emperors",
        "Kings",
        "Pharaohs",
        "Presidents"
    },
    2
),

new LessonQuestion(
    "Who was the first president of the United States?",
    new String[]{
        "Thomas Jefferson",
        "Abraham Lincoln",
        "George Washington",
        "John Adams"
    },
    2
),

new LessonQuestion(
    "Which war was fought from 1939 to 1945?",
    new String[]{
        "World War I",
        "The Cold War",
        "World War II",
        "The French Revolution"
    },
    2
)
};

        LessonQuestion[] level2 = {new LessonQuestion(
    "Which document limited the absolute power of English kings?",
    new String[]{
        "The Treaty of Versailles",
        "The French Declaration of Rights",
        "The Magna Carta",
        "The Russian Constitution"
    },
    2
),

new LessonQuestion(
    "Who unified China and became its first emperor?",
    new String[]{
        "Genghis Khan",
        "Suleiman the Magnificent",
        "Qin Shi Huang",
        "Nicholas II"
    },
    2
),

new LessonQuestion(
    "Which individual analyzed political power in the book 'The Prince'?",
    new String[]{
        "Martin Luther",
        "Alexander Fleming",
        "Michelangelo",
        "Niccolò Machiavelli"
    },
    3
)};
        LessonQuestion[] level3 = {
            new LessonQuestion(
    "Which event formally divided Africa among European colonial powers?",
    new String[]{
        "The Treaty of Paris",
        "The Peace of Westphalia",
        "The Berlin Conference",
        "The Congress of Vienna"
    },
    2
),

new LessonQuestion(
    "Which ancient civilization is credited with developing the concept of zero?",
    new String[]{
        "The Han Dynasty",
        "The Egyptians",
        "The Mayans",
        "The Romans"
    },
    2
),

new LessonQuestion(
    "Which leader is most closely associated with the Haitian Revolution?",
    new String[]{
        "Nelson Mandela",
        "Sun Tzu",
        "Toussaint Louverture",
        "Thucydides"
    },
    2
)
    };

        switch(page) {
                case 1: // Easy Level
    addLessonTitle(panel, "Lesson 1: Foundations of World History");

    addText(panel,
        "History helps us understand how the world we live in today was shaped by people, events, and ideas from the past. " +
        "In this lesson, we explore major civilizations, important leaders, and key historical events that every student " +
        "should know. These topics form the foundation for understanding politics, culture, science, and society.");
    addSpace(panel, 20);

    // ===== Early Civilizations =====
    addSubtitle(panel, "1. Early Civilizations and Empires");
    addText(panel,
        "Early civilizations developed when humans began settling in one place, farming, and forming organized societies. " +
        "These civilizations built cities, created systems of government, and developed writing, architecture, and religion. " +
        "Many of their achievements still influence the modern world.");
    addSpace(panel, 10);

    addBullet(panel, "Ancient Egypt was ruled by pharaohs who were believed to be gods on Earth.");
    addBullet(panel, "The Roman Empire created long-lasting systems of law, engineering, and architecture.");
    addBullet(panel, "The Inca civilization built advanced cities like Machu Picchu without modern tools.");
    addBullet(panel, "The Mongol Empire became the largest land empire in history under Genghis Khan.");
    addSpace(panel, 10);

    addExample(panel, "Example Question:", "Ancient Egyptian rulers were called:");
    addText(panel,
        "Answer: Pharaohs. They were both political leaders and religious figures responsible for maintaining order and balance.");
    addSpace(panel, 20);

    // ===== Important Historical Figures =====
    addSubtitle(panel, "2. Important Historical Figures");
    addText(panel,
        "Throughout history, individuals have played major roles in shaping nations and changing the world. " +
        "Leaders, explorers, inventors, and artists influenced how societies developed and how people understood their world.");
    addSpace(panel, 10);

    addBullet(panel, "George Washington became the first president of the United States after independence.");
    addBullet(panel, "Thomas Jefferson wrote the Declaration of Independence, outlining freedom and equality.");
    addBullet(panel, "Neil Armstrong became the first human to walk on the Moon in 1969.");
    addBullet(panel, "Leonardo da Vinci was a Renaissance artist and inventor who painted the Mona Lisa.");
    addSpace(panel, 10);

    addExample(panel, "Example Question:", "Who was the first president of the United States?");
    addText(panel,
        "Answer: George Washington. He helped establish the new nation and set many traditions still followed today.");
    addSpace(panel, 20);

    // ===== Wars and Major Events =====
    addSubtitle(panel, "3. Wars and Major World Events");
    addText(panel,
        "Wars and revolutions often change borders, governments, and societies. " +
        "They can lead to technological advances but also cause great human suffering. " +
        "Understanding these events helps explain modern global relationships.");
    addSpace(panel, 10);

    addBullet(panel, "World War II lasted from 1939 to 1945 and involved countries across the globe.");
    addBullet(panel, "World War I ended in 1918 and reshaped Europe.");
    addBullet(panel, "The Berlin Wall symbolized the division between East and West during the Cold War.");
    addBullet(panel, "The French Revolution began in 1789 and challenged monarchy and inequality.");
    addSpace(panel, 10);

    addExample(panel, "Example Question:", "Which war was fought from 1939 to 1945?");
    addText(panel,
        "Answer: World War II. It was one of the deadliest conflicts in history and changed global power structures.");
    addSpace(panel, 20);

    addText(panel,
        "By completing this lesson, you have learned about early civilizations, famous leaders, and major world events. " +
        "These ideas form the basis for deeper historical study.");
    

                    addSubtitle(panel, "Quick Test : History");
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
    addLessonTitle(panel, "Lesson 2: Revolutions, Empires, and Transforming Ideas");

    addText(panel,
        "This lesson explores how revolutions, political ideas, and powerful empires transformed societies. " +
        "Students will learn how systems of government changed and how ideas spread across continents.");
    addSpace(panel, 20);

    // ===== Revolutions =====
    addSubtitle(panel, "1. Revolutions and Political Change");
    addText(panel,
        "Revolutions occur when people challenge existing systems due to inequality, oppression, or injustice. " +
        "They often lead to dramatic changes in leadership, laws, and social structures.");
    addSpace(panel, 10);

    addBullet(panel, "The French Revolution promoted liberty, equality, and fraternity.");
    addBullet(panel, "The Russian Revolution of 1917 led to the rise of communism.");
    addBullet(panel, "The Treaty of Versailles officially ended World War I.");
    addBullet(panel, "The Magna Carta limited the absolute power of English kings.");
    addSpace(panel, 10);

    addExample(panel, "Example Question:", "Which revolution began in 1917?");
    addText(panel,
        "Answer: The Russian Revolution, which overthrew the monarchy and changed global politics.");
    addSpace(panel, 20);

    // ===== Empires and Leaders =====
    addSubtitle(panel, "2. Empires and Influential Leaders");
    addText(panel,
        "Empires expanded their influence through military conquest, trade, and diplomacy. " +
        "Strong leadership often determined whether an empire thrived or collapsed.");
    addSpace(panel, 10);

    addBullet(panel, "Genghis Khan united Mongol tribes into a vast empire.");
    addBullet(panel, "Suleiman the Magnificent expanded and strengthened the Ottoman Empire.");
    addBullet(panel, "Qin Shi Huang unified China and became its first emperor.");
    addBullet(panel, "Nicholas II was the final ruler of Imperial Russia.");
    addSpace(panel, 10);

    addExample(panel, "Example Question:", "Who was the first emperor of China?");
    addText(panel,
        "Answer: Qin Shi Huang. He unified China and standardized laws, currency, and writing.");
    addSpace(panel, 20);

    // ===== Ideas, Science, and Culture =====
    addSubtitle(panel, "3. Ideas, Science, and Culture");
    addText(panel,
        "Ideas and discoveries can change societies just as much as wars. " +
        "Philosophy, science, and art shape how people think and live.");
    addSpace(panel, 10);

    addBullet(panel, "Martin Luther challenged the Catholic Church during the Reformation.");
    addBullet(panel, "Machiavelli analyzed political power in 'The Prince'.");
    addBullet(panel, "Alexander Fleming discovered penicillin, saving millions of lives.");
    addBullet(panel, "Michelangelo’s artwork represents the height of Renaissance culture.");
    addSpace(panel, 10);

    addExample(panel, "Example Question:", "Who wrote 'The Prince'?");
    addText(panel,
        "Answer: Niccolò Machiavelli, who studied political leadership and power.");
    addSpace(panel, 20);

    addText(panel,
        "By completing this lesson, you have gained a deeper understanding of revolutions, empires, and ideas " +
        "that reshaped the world politically, culturally, and scientifically.");

addSubtitle(panel, "Quick Test: History");
                    addText(panel, "Try these questions! Select an answer and click 'Check Answers' to see your score and corrections.");
                    addSpace(panel, 20);

                    
                    addSubtitle(panel, "Quick Test : History");
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
    addLessonTitle(panel, "Lesson 3: Advanced Global History and Civilization");

    addText(panel,
        "This advanced lesson examines complex historical developments, including global treaties, ancient civilizations, " +
        "philosophical writings, and liberation movements. These topics require careful analysis and historical context.");
    addSpace(panel, 20);

    // ===== Treaties and Global Systems =====
    addSubtitle(panel, "1. Treaties and Global Power Structures");
    addText(panel,
        "International treaties and conferences shape global politics by defining borders, alliances, and economic cooperation. " +
        "They often emerge after major conflicts.");
    addSpace(panel, 10);

    addBullet(panel, "The Treaty of Paris (1951) helped create modern European unity.");
    addBullet(panel, "The Peace of Westphalia ended religious wars in Europe.");
    addBullet(panel, "The Berlin Conference divided Africa among European powers.");
    addSpace(panel, 10);

    addExample(panel, "Example Question:", "Which conference divided Africa among European powers?");
    addText(panel,
        "Answer: The Berlin Conference of 1884–1885.");
    addSpace(panel, 20);

    // ===== Ancient Knowledge and Civilizations =====
    addSubtitle(panel, "2. Ancient Knowledge and Civilizations");
    addText(panel,
        "Ancient civilizations made remarkable contributions to mathematics, writing, engineering, and governance. " +
        "Many modern systems are built upon these early innovations.");
    addSpace(panel, 10);

    addBullet(panel, "The Mayans developed advanced mathematics, including the concept of zero.");
    addBullet(panel, "The Han Dynasty invented paper, revolutionizing communication.");
    addBullet(panel, "Hatshepsut ruled Egypt as one of its first female pharaohs.");
    addBullet(panel, "Teotihuacan was a major Mesoamerican city of unknown origin.");
    addSpace(panel, 10);

    addExample(panel, "Example Question:", "Which civilization developed the concept of zero?");
    addText(panel,
        "Answer: The Mayan civilization, which made major advances in mathematics and astronomy.");
    addSpace(panel, 20);

    // ===== Ideas, Movements, and Leadership =====
    addSubtitle(panel, "3. Ideas, Movements, and Leadership");
    addText(panel,
        "Ideas and social movements have challenged injustice and transformed societies. " +
        "Leaders and thinkers inspired change through philosophy, resistance, and reform.");
    addSpace(panel, 10);

    addBullet(panel, "Sun Tzu wrote 'The Art of War', influencing military strategy.");
    addBullet(panel, "Thucydides set standards for historical writing.");
    addBullet(panel, "Toussaint Louverture led the Haitian Revolution.");
    addBullet(panel, "Nelson Mandela fought apartheid and promoted reconciliation.");
    addSpace(panel, 10);

    addExample(panel, "Example Question:", "Who led the Haitian Revolution?");
    addText(panel,
        "Answer: Toussaint Louverture, who led enslaved people to freedom and independence.");
    addSpace(panel, 20);

    addText(panel,
        "By completing this lesson, you have explored advanced historical systems, civilizations, treaties, and movements. " +
        "These topics provide a deep understanding of how global history continues to shape the modern world.");
        addSubtitle(panel, "Quick Test: History");
                    addText(panel, "Try these questions! Select an answer and click 'Check Answers' to see your score and corrections.");
                    addSpace(panel, 20);

    addSpace(panel, 25);
                    addSubtitle(panel, "Quick Test : History");
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