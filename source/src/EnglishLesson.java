import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EnglishLesson extends JFrame {
    private LessonsMenu parentMenu;
    private int currentPage = 1;
    private int totalPages = 3;
    private int userId;
    private dashboard dashboardRef;



    public EnglishLesson(LessonsMenu parentMenu, int userId) {
        this.parentMenu = parentMenu;
        this.userId = userId;
        this.dashboardRef = dashboardRef;
    
        setupWindow();
        setupLayout();
    }
    
    private void setupWindow() {
        setTitle("English Lesson ");
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
        
        JLabel titleLabel = new JLabel(" ENGLISH LESSON ");
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
            new LessonQuestion("Which sentence is correct?",
        new String[]{
            "She go to school.",
            "She goes to school.",
            "She going to school.",
            "She to go school."
        }, 1),

    new LessonQuestion("Choose the correct article: ___ apple a day keeps the doctor away.",
        new String[]{"A", "An", "The", "No article needed"}, 1),

    new LessonQuestion("What is the plural of 'child'?",
        new String[]{"Childs", "Children", "Childes", "Childen"}, 1),
        };
        LessonQuestion[] level2 = {
            new LessonQuestion("Which sentence uses the subjunctive mood correctly?",
        new String[]{
            "I wish I was taller.",
            "I wish I were taller.",
            "If I was you, I'd go.",
            "I suggest he goes now."
        }, 1),

    new LessonQuestion("Which sentence is in passive voice?",
        new String[]{
            "The cat chased the mouse.",
            "The mouse was chased by the cat.",
            "She wrote a letter.",
            "They are building a house."
        }, 1),

    new LessonQuestion("What does 'ephemeral' mean?",
        new String[]{
            "Lasting a very short time",
            "Beautiful",
            "Difficult to understand",
            "Very large"
        }, 0),
        };
        LessonQuestion[] level3 = {
            new LessonQuestion("Which sentence uses the conditional perfect correctly?",
        new String[]{
            "If I would have known, I would have come.",
            "If I had known, I would have come.",
            "If I knew, I would come.",
            "If I know, I will come."
        }, 1),

    new LessonQuestion("What does 'sesquipedalian' mean?",
        new String[]{
            "Using long words",
            "Walking sideways",
            "Having six feet",
            "Speaking multiple languages"
        }, 0),

    new LessonQuestion("A 'non sequitur' is:",
        new String[]{
            "A logical conclusion",
            "A statement that does not logically follow",
            "A type of poem",
            "A grammatical error"
        }, 1),


        };

        switch(page) {
                case 1:  

                    addLessonTitle(panel, "English Language Foundations");
                    addText(panel,
                        "English is a rich and flexible language used to communicate ideas, emotions, and information. " +
                        "In this lesson, you will explore grammar, vocabulary, literature, and writing skills, " +
                        "progressing from basic rules to advanced analysis.");
                    addSpace(panel, 25);

                    addSubtitle(panel, "Basic Grammar");
                    addText(panel,
                        "Grammar provides the structure of language. Understanding how sentences are formed helps ensure " +
                        "clarity and correctness in both spoken and written English.");

                    addBullet(panel, "Subject–Verb Agreement: Singular subjects require singular verbs (e.g., 'She goes to school').");
                    addBullet(panel, "Articles: 'A' is used before consonant sounds, while 'an' is used before vowel sounds.");
                    addBullet(panel, "Plural Nouns: Some nouns form irregular plurals, such as 'child' becoming 'children'.");
                    addBullet(panel, "Parts of Speech: Nouns name people, places, things, or ideas (e.g., 'happiness').");
                    addBullet(panel, "Verb Tenses: Actions happening now use the present continuous tense (e.g., 'is reading').");

                    addExample(panel, "Example:", "He is reading a book right now.");
                    addSpace(panel, 20);

                    addSubtitle(panel, "Basic Vocabulary");
                    addText(panel,
                        "Vocabulary helps express meaning precisely. Knowing synonyms, antonyms, and word meanings " +
                        "strengthens comprehension and expression.");

                    addBullet(panel, "Synonyms: Words with similar meanings (happy → joyful).");
                    addBullet(panel, "Antonyms: Words with opposite meanings (hot → cold).");
                    addBullet(panel, "Word Meaning: 'Benevolent' describes someone who is kind.");
                    addBullet(panel, "Spelling Accuracy: Correct spelling improves clarity and professionalism.");
                    addBullet(panel, "Definitions: A synonym is a word with the same or similar meaning.");

                    addSpace(panel, 20);

                    addSubtitle(panel, "Introduction to Literature and Writing");
                    addText(panel,
                        "Literature introduces storytelling techniques, while writing skills help convey ideas effectively.");

                    addBullet(panel, "Authors: William Shakespeare wrote the play 'Romeo and Juliet'.");
                    addBullet(panel, "Characters: The protagonist is the main character in a story.");
                    addBullet(panel, "Literary Devices: Alliteration repeats beginning sounds for effect.");
                    addBullet(panel, "Punctuation: A question mark indicates a question.");
                    addBullet(panel, "Capitalization: Proper nouns and the word 'I' must always be capitalized.");

                    addExample(panel, "Example:", "I live in New York.");
                    addSpace(panel, 30);

                    addSubtitle(panel, "Quick Test : English");
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
                    addLessonTitle(panel, "Developing English Skills");
    addText(panel,
        "At the intermediate level, English learners refine their understanding of sentence structure, " +
        "word choice, literary meaning, and writing style.");

    addSpace(panel, 20);
    addSubtitle(panel, "Intermediate Grammar and Usage");

    addBullet(panel, "Subjunctive Mood: Used to express wishes or hypothetical situations ('I wish I were taller').");
    addBullet(panel, "Modifiers: A dangling modifier occurs when the subject is unclear.");
    addBullet(panel, "Agreement Rules: When subjects are joined by 'neither/nor', the verb agrees with the closest noun.");
    addBullet(panel, "Voice: Passive voice emphasizes the action rather than the subject.");
    addBullet(panel, "Sentence Variety: Avoid redundancy by eliminating unnecessary repetition.");

    addExample(panel, "Example:", "The mouse was chased by the cat.");
    addSpace(panel, 20);

    addSubtitle(panel, "Vocabulary and Idiomatic Language");
    addText(panel,
        "English contains many idioms and advanced vocabulary words whose meanings cannot be guessed literally.");

    addBullet(panel, "Advanced Vocabulary: 'Ephemeral' means lasting a very short time.");
    addBullet(panel, "Idioms: 'Hit the roof' means to become very angry.");
    addBullet(panel, "Homophones: Words that sound the same but have different meanings (write/right).");
    addBullet(panel, "Nuanced Meaning: 'Specious' describes something that seems correct but is misleading.");

    addSpace(panel, 20);

    addSubtitle(panel, "Literature and Writing Structure");
    addText(panel,
        "Analyzing literature involves recognizing themes, symbols, tone, and structure.");

    addBullet(panel, "Symbolism: In 'Moby Dick', the whale represents obsession and the unknown.");
    addBullet(panel, "Figures of Speech: A metaphor directly compares two unlike things.");
    addBullet(panel, "Tone: Word choice can create mood, such as loneliness or tension.");
    addBullet(panel, "Thesis Statements: Clearly state the main argument of an essay.");
    addBullet(panel, "Sentence Types: Compound-complex sentences combine multiple ideas.");

    addSpace(panel, 30);
                    
                    addSubtitle(panel, "Quick Test : English");
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
                    addLessonTitle(panel, "Advanced Language, Literature, and Rhetoric");
    addText(panel,
        "Advanced English explores deep grammatical accuracy, rhetorical techniques, literary theory, " +
        "and the historical development of language.");

    addSpace(panel, 20);
    addSubtitle(panel, "Advanced Grammar and Syntax");

    addBullet(panel, "Relative Pronouns: 'Who' is used for subjects; 'whom' is used for objects.");
    addBullet(panel, "Conditional Perfect: Used to describe unreal past situations.");
    addBullet(panel, "Parallel Structure: Ensures balance in complex sentences.");
    addBullet(panel, "Subordinate Clauses: Add detail but cannot stand alone.");

    addSpace(panel, 20);

    addSubtitle(panel, "Advanced Vocabulary and Rhetoric");
    addText(panel,
        "Sophisticated language use allows for precision, persuasion, and stylistic elegance.");

    addBullet(panel, "Rare Vocabulary: 'Sesquipedalian' refers to using long words.");
    addBullet(panel, "Logical Errors: A non sequitur does not logically follow.");
    addBullet(panel, "Allusion: References historical or mythological figures.");
    addBullet(panel, "Rhetorical Devices: Chiasmus reverses structure for emphasis.");
    addBullet(panel, "Argumentation: A straw man misrepresents an opposing argument.");

    addExample(panel, "Example:", "Ask not what your country can do for you...");
    addSpace(panel, 20);

    addSubtitle(panel, "Linguistics and Literary Theory");
    addText(panel,
        "Linguistics studies how language works, while literary theory examines how texts create meaning.");

    addBullet(panel, "Language Family: English belongs to the Germanic family.");
    addBullet(panel, "Historical Change: The Great Vowel Shift altered English pronunciation.");
    addBullet(panel, "Morphemes: The smallest meaningful units of language.");
    addBullet(panel, "Code-Switching: Alternating between languages or dialects.");
    addBullet(panel, "Theory: Deconstruction analyzes contradictions and binary oppositions in texts.");

    addSpace(panel, 25);
                    addSubtitle(panel, "Quick Test : English");
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