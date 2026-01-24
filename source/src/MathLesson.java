import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MathLesson extends JFrame {
    private LessonsMenu parentMenu;
    private int currentPage = 1;
    private int totalPages = 3;
    private int userId;
    private dashboard dashboardRef;
    
    
    public MathLesson(LessonsMenu parentMenu, int userId,dashboard dashboardRef) {
        this.parentMenu = parentMenu;
        this.userId = userId;
        this.dashboardRef = dashboardRef;
    
        setupWindow();
        setupLayout();
    }
    
    private void setupWindow() {
        setTitle("Math Lesson - Basic Algebra");
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
        
        JLabel titleLabel = new JLabel(" MATHEMATICS: ALGEBRA BASICS");
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
            new LessonQuestion("What is 5 + 7?", new String[]{"12","35","42","30"}, 0),
            new LessonQuestion("Solve: x + 5 = 12", new String[]{"x = 7","x = 17","x = 6","x = 8"}, 0),
            new LessonQuestion("Perimeter of square side 3?", new String[]{"12","9","6","15"}, 0)
        };
        LessonQuestion[] level2 = {
            new LessonQuestion("Solve: 3x + 7 = 22", new String[]{"x = 5","x = 15","x = 7","x = 6"}, 0),
            new LessonQuestion("Area of circle radius 5? (pi \u2248 3.14)", new String[]{"78.5","31.4","15.7","25"}, 0)
        };
        LessonQuestion[] level3 = {
            new LessonQuestion("Solve: x^2 - 5x + 6 = 0", new String[]{"x = 2,3","x = 1,6","x = -2,-3","x = 0,5"}, 0),
            new LessonQuestion("Derivative of 3x^2 + 4x - 5", new String[]{"6x + 4","3x + 4","6x^2 + 4","x^3 + 2x^2 - 5x"}, 0),
            new LessonQuestion("i^2 = ?", new String[]{"-1","1","i","0"}, 0)
        };

        switch(page) {
            case 1:
                    addLessonTitle(panel, "Lesson 1: Foundations of Mathematics");

                    addText(panel, 
                        "In this lesson, we will review the basic building blocks of mathematics. " +
                        "These skills are essential because they appear in almost every area of math, " +
                        "including algebra, geometry, and problem-solving in real life.");
                    addSpace(panel, 20);

                    // ===== Basic Arithmetic =====
                    addSubtitle(panel, "1. Basic Arithmetic Operations");
                    addText(panel,
                        "Arithmetic involves working with numbers using operations such as addition, " +
                        "subtraction, multiplication, and division. Mastering these operations helps us " +
                        "solve everyday problems quickly and accurately.");
                    addSpace(panel, 10);

                    addBullet(panel, "Addition combines numbers together (example: 5 + 7 = 12).");
                    addBullet(panel, "Subtraction finds the difference between numbers (example: 15 - 8 = 7).");
                    addBullet(panel, "Multiplication is repeated addition (example: 6 * 9 = 54).");
                    addBullet(panel, "Division splits a number into equal parts (example: 48 / 6 = 8).");
                    addSpace(panel, 15);

                    addExample(panel, "Worked Example:", "If you divide 48 by 6, each group contains 8.");
                    addSpace(panel, 20);

                    // ===== Fractions and Decimals =====
                    addSubtitle(panel, "2. Fractions and Decimals");
                    addText(panel,
                        "Fractions and decimals are different ways of representing parts of a whole. " +
                        "Being able to convert between them makes calculations easier and clearer.");
                    addSpace(panel, 10);

                    addBullet(panel, "A fraction shows part of a whole (example: 3/4).");
                    addBullet(panel, "A decimal is another way to write a fraction (example: 0.75).");
                    addSpace(panel, 10);

                    addExample(panel, "Example:", 
                        "The fraction 3/4 can be converted to the decimal 0.75 by dividing 3 by 4.");
                    addSpace(panel, 20);

                    // ===== Introduction to Algebra =====
                    addSubtitle(panel, "3. Introduction to Algebra");
                    addText(panel,
                        "Algebra uses letters, called variables, to represent unknown numbers. " +
                        "By solving equations, we can find the value of these unknowns.");
                    addSpace(panel, 10);

                    addBullet(panel, "A variable represents an unknown value (such as x or y).");
                    addBullet(panel, "An equation shows that two expressions are equal.");
                    addSpace(panel, 10);

                    addExample(panel, "Solved Equation:", 
                        "In x + 5 = 12, subtract 5 from both sides to find x = 7.");
                    addSpace(panel, 20);

                    // ===== Geometry Basics =====
                    addSubtitle(panel, "4. Geometry Basics");
                    addText(panel,
                        "Geometry focuses on shapes, sizes, and measurements. " +
                        "Understanding area, perimeter, and angles helps us describe space accurately.");
                    addSpace(panel, 10);

                    addBullet(panel, "Area measures the space inside a shape.");
                    addBullet(panel, "Perimeter is the distance around a shape.");
                    addBullet(panel, "A right angle measures exactly 90 degrees.");
                    addSpace(panel, 10);

                    addExample(panel, "Example:", 
                        "A rectangle with sides 5 and 4 has an area of 20 square units.");
                    addSpace(panel, 20);

                    // ===== Percentages =====
                    addSubtitle(panel, "5. Percentages");
                    addText(panel,
                        "Percentages represent parts of a whole out of 100. " +
                        "They are commonly used in discounts, grades, and statistics.");
                    addSpace(panel, 10);

                    addBullet(panel, "10% means 10 out of 100.");
                    addBullet(panel, "50% is equal to one half.");
                    addSpace(panel, 10);

                    addExample(panel, "Example:", 
                        "10% of 200 is found by multiplying 200 by 0.10, which equals 20.");
                    addSpace(panel, 20);

                    // ===== Patterns and Numbers =====
                    addSubtitle(panel, "6. Number Patterns and Properties");
                    addText(panel,
                        "Recognizing number patterns and properties helps develop logical thinking " +
                        "and prepares students for more advanced mathematics.");
                    addSpace(panel, 10);

                    addBullet(panel, "Even numbers are divisible by 2.");
                    addBullet(panel, "Prime numbers have exactly two factors.");
                    addBullet(panel, "Patterns help predict the next number in a sequence.");
                    addSpace(panel, 10);

                    addExample(panel, "Example:", 
                        "In the sequence 2, 4, 6, 8, the next number is 10.");
                    addSpace(panel, 20);

                    addText(panel,
                        "By completing this lesson, you have strengthened your understanding of " +
                        "core mathematical concepts that will be used throughout your studies.");
                    
                    addSubtitle(panel, "Quick Test: Algebra & Math");
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
                    addLessonTitle(panel, "Lesson 2: Expanding Your Math Skills");

                    addText(panel,
                        "In this lesson, we will explore more advanced topics, including algebraic equations, " +
                        "geometry formulas, fractions, decimals, percentages, exponents, and basic probability. " +
                        "These concepts build on the basics and prepare you for more challenging problems.");
                    addSpace(panel, 20);

                    // ===== Algebra: Solving Equations =====
                    addSubtitle(panel, "1. Algebra: Solving Equations");
                    addText(panel,
                        "Algebra allows us to solve for unknowns in equations. " +
                        "Equations may require multiple steps and applying properties like the distributive property.");
                    addSpace(panel, 10);

                    addBullet(panel, "To solve 3x + 7 = 22, subtract 7 from both sides to get 3x = 15, then divide by 3: x = 5.");
                    addBullet(panel, "For 2(x - 3) = 10, first divide both sides by 2: x - 3 = 5, then add 3: x = 8.");
                    addSpace(panel, 10);

                    addExample(panel, "Factoring Example:", 
                        "Factor x^2 + 5x + 6 by finding two numbers that multiply to 6 and add to 5: (x + 2)(x + 3).");
                    addSpace(panel, 20);

                    // ===== Geometry: Area, Volume, Pythagoras =====
                    addSubtitle(panel, "2. Geometry: Area, Volume, and Pythagoras");
                    addText(panel,
                        "Geometry at this level includes circles, cubes, and right triangles. " +
                        "Knowing formulas allows you to calculate areas, volumes, and side lengths accurately.");
                    addSpace(panel, 10);

                    addBullet(panel, "Area of a circle: area = pi * r^2. For radius 5, area = pi * 5^2 = 78.5.");
                    addBullet(panel, "Volume of a cube: volume = side^3. For side 4, volume = 64.");
                    addBullet(panel, "Pythagoras theorem: a^2 + b^2 = c^2. For a = 3 and b = 4, c^2 = 9 + 16 = 25, so c = 5.");
                    addSpace(panel, 20);

                    // ===== Fractions and Decimals =====
                    addSubtitle(panel, "3. Fractions and Decimals");
                    addText(panel,
                        "We extend fraction and decimal knowledge by multiplying fractions and converting decimals.");
                    addSpace(panel, 10);

                    addBullet(panel, "Multiply fractions: (2/3) * (3/4) = 6/12 = 1/2.");
                    addBullet(panel, "Convert decimal to fraction: 0.375 = 3/8.");
                    addSpace(panel, 10);

                    addExample(panel, "Example:", 
                        "Multiplying 2/3 by 3/4 gives 1/2, which can also be expressed as 0.5 in decimal form.");
                    addSpace(panel, 20);

                    // ===== Percentages =====
                    addSubtitle(panel, "4. Percentages");
                    addText(panel,
                        "Percentages are useful for calculating parts of quantities, increases, and discounts.");
                    addSpace(panel, 10);

                    addBullet(panel, "25% of 480 = 480 * 0.25 = 120.");
                    addBullet(panel, "To increase 200 by 15%, calculate 200 * 1.15 = 230.");
                    addSpace(panel, 20);

                    // ===== Exponents and Roots =====
                    addSubtitle(panel, "5. Exponents and Roots");
                    addText(panel,
                        "Exponents represent repeated multiplication. Roots are the inverse of exponentiation.");
                    addSpace(panel, 10);

                    addBullet(panel, "2^3 * 2^2 = 2^(3+2) = 2^5 = 32.");
                    addBullet(panel, "5^-2 = 1 / (5^2) = 1/25.");
                    addBullet(panel, "Cube root: cbrt(27) = 3.");
                    addBullet(panel, "Square root: sqrt(25 + 144) = sqrt(169) = 13.");
                    addSpace(panel, 20);

                    // ===== Sequences =====
                    addSubtitle(panel, "6. Sequences");
                    addText(panel,
                        "Sequences are ordered lists of numbers. Recognizing patterns allows us to predict future terms.");
                    addSpace(panel, 10);

                    addBullet(panel, "Square numbers sequence: 1, 4, 9, 16, 25, next = 36.");
                    addBullet(panel, "Fibonacci sequence: 1, 1, 2, 3, 5, 8, next = 13.");
                    addSpace(panel, 20);

                    // ===== Probability =====
                    addSubtitle(panel, "7. Probability");
                    addText(panel,
                        "Probability measures the chance of an event occurring. It is expressed as a fraction between 0 and 1.");
                    addSpace(panel, 10);

                    addBullet(panel, "Chance of heads in a coin flip = 1/2.");
                    addBullet(panel, "Chance of rolling a 6 on a die = 1/6.");
                    addSpace(panel, 20);

                    // ===== Measurements =====
                    addSubtitle(panel, "8. Measurements");
                    addText(panel,
                        "Converting units and measuring time accurately are essential skills.");
                    addSpace(panel, 10);

                    addBullet(panel, "Convert 2.5 km to meters: 2.5 * 1000 = 2500 meters.");
                    addBullet(panel, "Convert 1 hour 45 minutes to minutes: 60 + 45 = 105 minutes.");
                    addSpace(panel, 20);

                    addText(panel,
                        "By completing this lesson, you have learned more advanced math concepts that " +
                        "will prepare you for algebra, geometry, and problem-solving at higher levels.");
                    
                    addSubtitle(panel, "Quick Test: Algebra & Math");
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
                    addLessonTitle(panel, "Lesson 3: Advanced Mathematics");

                    addText(panel,
                        "In this lesson, we will explore advanced mathematics topics. " +
                        "These include complex equations, calculus, trigonometry, complex numbers, logarithms, matrices, series, advanced geometry, and statistics. " +
                        "Mastering these topics prepares you for higher-level problem solving and real-world applications.");
                    addSpace(panel, 20);

                    // ===== Advanced Algebra =====
                    addSubtitle(panel, "1. Advanced Algebra");
                    addText(panel,
                        "Advanced algebra involves quadratic equations, systems of equations, and simplifying expressions.");
                    addSpace(panel, 10);

                    addBullet(panel, "Quadratic equation: x^2 - 5x + 6 = 0 factors to (x - 2)(x - 3), so x = 2 or x = 3.");
                    addBullet(panel, "System of equations: x + y = 10, x - y = 4. Solve to get x = 7, y = 3.");
                    addBullet(panel, "Simplify exponents: (x^3 * x^4) / x^2 = x^(3+4-2) = x^5.");
                    addSpace(panel, 20);

                    // ===== Calculus Basics =====
                    addSubtitle(panel, "2. Calculus Basics");
                    addText(panel,
                        "Calculus introduces derivatives, integrals, and limits, which are tools for analyzing change and accumulation.");
                    addSpace(panel, 10);

                    addBullet(panel, "Derivative example: d/dx (3x^2 + 4x - 5) = 6x + 4.");
                    addBullet(panel, "Integral example: \u222B(2x + 3) dx = x^2 + 3x + C.");
                    addBullet(panel, "Limit example: lim(x->2) (x^2 - 4) / (x - 2) = 4.");
                    addSpace(panel, 20);

                    // ===== Trigonometry =====
                    addSubtitle(panel, "3. Trigonometry");
                    addText(panel,
                        "Trigonometry studies the relationships between angles and sides of triangles.");
                    addSpace(panel, 10);

                    addBullet(panel, "Identity: sin^2(theta) + cos^2(theta) = 1. (theta is -> \u03B8)");
                    addBullet(panel, "cos(60 degrees) = 1/2.");
                    addBullet(panel, "tan(45 degrees) = 1.");
                    addSpace(panel, 20);

                    // ===== Complex Numbers =====
                    addSubtitle(panel, "4. Complex Numbers");
                    addText(panel,
                        "Complex numbers include a real and imaginary part. The imaginary unit i satisfies i^2 = -1.");
                    addSpace(panel, 10);

                    addBullet(panel, "i^2 = -1.");
                    addBullet(panel, "(3 + 4i) + (2 - i) = 5 + 3i.");
                    addSpace(panel, 20);

                    // ===== Logarithms =====
                    addSubtitle(panel, "5. Logarithms");
                    addText(panel,
                        "Logarithms are the inverse of exponentiation. They help solve equations where the variable is an exponent.");
                    addSpace(panel, 10);

                    addBullet(panel, "log2(8) = 3 because 2^3 = 8.");
                    addBullet(panel, "log10(1000) = 3 because 10^3 = 1000.");
                    addBullet(panel, "e^(ln5) = 5.");
                    addSpace(panel, 20);

                    // ===== Matrices =====
                    addSubtitle(panel, "6. Matrices");
                    addText(panel,
                        "Matrices are arrays of numbers used in linear algebra for solving systems and transformations.");
                    addSpace(panel, 10);

                    addBullet(panel, "Determinant of [[2,3],[1,4]] = (2*4) - (3*1) = 5.");
                    addSpace(panel, 20);

                    // ===== Series and Sums =====
                    addSubtitle(panel, "7. Series and Sums");
                    addText(panel,
                        "A series is the sum of the terms of a sequence. Calculating sums is fundamental in many areas of math.");
                    addSpace(panel, 10);

                    addBullet(panel, "Sum of 1 to 100 = 5050.");
                    addBullet(panel, "Sum of 2 + 4 + 6 + ... + 20 = 110.");
                    addSpace(panel, 20);

                    // ===== Advanced Geometry =====
                    addSubtitle(panel, "8. Advanced Geometry");
                    addText(panel,
                        "Advanced geometry includes calculating volumes, diagonals, and understanding 3D shapes.");
                    addSpace(panel, 10);

                    addBullet(panel, "Volume of a sphere with radius 3: V = 4/3 * pi * 3^3 = 36 * pi.");
                    addBullet(panel, "Diagonal of a cube with side 5: diagonal = 5 * sqrt(3).");
                    addSpace(panel, 20);

                    // ===== Statistics =====
                    addSubtitle(panel, "9. Statistics");
                    addText(panel,
                        "Statistics help us summarize and interpret data through measures such as mean and median.");
                    addSpace(panel, 10);

                    addBullet(panel, "Mean of 4, 8, 6, 5, 7: (4 + 8 + 6 + 5 + 7) / 5 = 6.");
                    addBullet(panel, "Median of 1, 3, 5, 7, 9: middle value = 5.");
                    addSpace(panel, 20);

                    addText(panel,
                        "Completing this lesson equips you with advanced mathematical tools for problem-solving, " +
                        "preparing you for higher-level mathematics, science, and engineering applications.");
                    
                    addSubtitle(panel, "Quick Test: Algebra & Math");
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
        Quiz_menu quizMenu = new Quiz_menu(userId, this,dashboardRef);
        quizMenu.setVisible(true);
        this.setVisible(false);
    }
    
    private void returnToLessonsMenu() {
        if (parentMenu != null) {
            parentMenu.returnToLessonsMenu();
        }
        this.dispose();
    }
    private void backToDashboard() {
        dashboardRef.refreshDashboard();
        dashboardRef.setVisible(true);
        this.dispose();
    }
}