import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ComputerLesson extends JFrame {
    private LessonsMenu parentMenu;
    private int currentPage = 1;
    private int totalPages = 3;
    private int userId;
    private dashboard dashboardRef;



    public ComputerLesson(LessonsMenu parentMenu, int userId) {
        this.parentMenu = parentMenu;
        this.userId = userId;
        this.dashboardRef = dashboardRef;
    
        setupWindow();
        setupLayout();
    }
    
    private void setupWindow() {
        setTitle("Computer Lesson ");
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
        
        JLabel titleLabel = new JLabel(" COMPUTER LESSON ");
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
          new LessonQuestion("What does CPU stand for?",
        new String[]{"Central Processing Unit", "Computer Processing Unit", "Central Process Unit", "Computer Personal Unit"}, 0),

    new LessonQuestion("Which of these is an operating system?",
        new String[]{"Microsoft Word", "Windows", "Google Chrome", "Photoshop"}, 1),

    new LessonQuestion("What does RAM stand for?",
        new String[]{"Random Access Memory", "Read Access Memory", "Random Available Memory", "Read Available Memory"}, 0),
};

        LessonQuestion[] level2 = { new LessonQuestion("Which data structure uses LIFO principle?",
        new String[]{"Queue", "Stack", "Array", "Linked List"}, 1),

    new LessonQuestion("What is the time complexity of binary search?",
        new String[]{"O(n)", "O(log n)", "O(n^2)", "O(1)"}, 1),

    new LessonQuestion("Which is NOT an OOP principle?",
        new String[]{"Encapsulation", "Inheritance", "Compilation", "Polymorphism"}, 2),};
        LessonQuestion[] level3 = {new LessonQuestion("What is memoization?",
        new String[]{"A sorting technique", "Storing results of expensive calls", "A type of loop", "Memory allocation"}, 1),

    new LessonQuestion("Which algorithm prevents deadlock?",
        new String[]{"Bankers Algorithm", "Quick Sort", "Dijkstra", "Binary Search"}, 0),

    new LessonQuestion("What is a monad in functional programming?",
        new String[]{"A design pattern", "A programming structure for computations", "A type of loop", "An error handler"}, 1),};

        switch(page) {
                case 1: // Easy Level
    addLessonTitle(panel, "Lesson 1: Foundations of Computers");

    addText(panel, 
        "In this lesson, we will explore the fundamental concepts of computers. " +
        "Understanding these basics is essential because they form the foundation for programming, " +
        "software development, and everyday use of technology.");
    addSpace(panel, 20);

    // ===== Computer Hardware =====
    addSubtitle(panel, "1. Computer Hardware");
    addText(panel, 
        "Computers are made up of hardware components that work together to perform tasks. " +
        "The CPU, or Central Processing Unit, is often called the brain of the computer. " +
        "It processes instructions and performs calculations required by programs.");
    addSpace(panel, 10);

    addBullet(panel, "CPU: Executes instructions and calculations.");
    addBullet(panel, "RAM (Random Access Memory): Temporary memory for programs in use.");
    addBullet(panel, "Hard Drive: Permanent storage for files and programs.");
    addBullet(panel, "Input Devices: Allow the user to interact with the computer (e.g., keyboard, mouse).");
    addBullet(panel, "Output Devices: Display or produce information (e.g., monitor, printer).");
    addSpace(panel, 10);

    addExample(panel, "Example Question:", "What does CPU stand for?");
    addText(panel, "Answer: Central Processing Unit. It is the primary processing component of the computer.");
    addSpace(panel, 20);

    // ===== Software and Operating Systems =====
    addSubtitle(panel, "2. Software and Operating Systems");
    addText(panel,
        "Software is a collection of programs and applications that tell the computer how to perform tasks. " +
        "Operating systems like Windows or macOS manage the computer's hardware and provide a platform for software to run.");
    addSpace(panel, 10);

    addBullet(panel, "Operating System: Manages hardware and software resources.");
    addBullet(panel, "Applications: Programs that perform specific tasks, such as web browsers or word processors.");
    addBullet(panel, "Drivers: Software that allows the OS to communicate with hardware.");
    addSpace(panel, 10);

    addExample(panel, "Example Question:", "Which of these is an operating system?");
    addText(panel, "Answer: Windows. It manages the computer's resources and provides an interface for users.");
    addSpace(panel, 20);

    // ===== Internet and Networking =====
    addSubtitle(panel, "3. Internet and Networking");
    addText(panel,
        "The Internet connects computers worldwide, allowing communication and information sharing. " +
        "The World Wide Web (WWW) is a collection of web pages accessible through browsers. " +
        "Web technologies like HTML help structure these pages, and browsers like Firefox or Chrome allow users to view them.");
    addSpace(panel, 10);

    addBullet(panel, "WWW (World Wide Web): A system of interlinked web pages.");
    addBullet(panel, "HTML (HyperText Markup Language): Standard language for creating web pages.");
    addBullet(panel, "Web Browser: Software that displays web pages (e.g., Firefox, Chrome).");
    addSpace(panel, 10);

    addExample(panel, "Example Question:", "What does WWW stand for?");
    addText(panel, "Answer: World Wide Web. It allows users to access information on the Internet.");
    addSpace(panel, 20);

    // ===== Basic Computer Usage =====
    addSubtitle(panel, "4. Basic Computer Usage");
    addText(panel,
        "Understanding how to interact with computers is essential. Keys like Shift are used to type capital letters, " +
        "and USB ports allow devices like flash drives to connect and transfer data. Knowing these basics helps you navigate " +
        "computers efficiently.");
    addSpace(panel, 10);

    addBullet(panel, "Shift Key: Used to type capital letters.");
    addBullet(panel, "USB (Universal Serial Bus): Interface for connecting devices.");
    addBullet(panel, "Mouse: Pointing device for selecting and interacting with elements on the screen.");
    addSpace(panel, 10);

    addExample(panel, "Example Question:", "Which key is used to type capital letters?");
    addText(panel, "Answer: Shift. Holding the Shift key while pressing a letter key types a capital letter.");
    addSpace(panel, 20);

    addText(panel,
        "By completing this lesson, you have learned the basic components of computers, the role of software and operating systems, " +
        "how the Internet and web technologies work, and how to use a computer effectively.");
    

                    addSubtitle(panel, "Quick Test : Computer");
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
    addLessonTitle(panel, "Lesson 2: Programming and Data Concepts");

    addText(panel, 
        "In this lesson, we explore the fundamentals of programming, data structures, algorithms, and databases. " +
        "These concepts are crucial for anyone learning to develop software or work with computers at a deeper level.");
    addSpace(panel, 20);

    // ===== Data Structures =====
    addSubtitle(panel, "1. Data Structures");
    addText(panel,
        "Data structures help organize and manage information efficiently. " +
        "Common structures include stacks, queues, arrays, and linked lists. " +
        "Each has specific rules for how data is added, removed, and accessed.");
    addSpace(panel, 10);

    addBullet(panel, "Stack: Follows Last In, First Out (LIFO), meaning the last item added is the first removed.");
    addBullet(panel, "Queue: Follows First In, First Out (FIFO), so the first item added is the first removed.");
    addBullet(panel, "Array: Stores elements in a fixed-size sequence, accessible by index.");
    addBullet(panel, "Linked List: Elements (nodes) are linked together, allowing flexible memory usage.");
    addSpace(panel, 10);

    addExample(panel, "Example Question:", "Which data structure uses LIFO principle?");
    addText(panel, "Answer: Stack. The last element added is the first one to be removed.");
    addSpace(panel, 20);

    // ===== Algorithms =====
    addSubtitle(panel, "2. Algorithms");
    addText(panel,
        "Algorithms are step-by-step procedures for solving problems efficiently. " +
        "Understanding the performance of an algorithm is important, often measured by time complexity.");
    addSpace(panel, 10);

    addBullet(panel, "Binary Search: Efficiently finds a value in a sorted list (O(log n) complexity).");
    addBullet(panel, "Dijkstra's Algorithm: Finds the shortest path between nodes in a graph.");
    addBullet(panel, "Sorting Algorithms: Organize data in a specific order (e.g., Bubble Sort, Quick Sort).");
    addSpace(panel, 10);

    addExample(panel, "Example Question:", "What is the time complexity of binary search?");
    addText(panel, "Answer: O(log n). Binary search repeatedly divides the search range in half.");
    addSpace(panel, 20);

    // ===== Programming Concepts =====
    addSubtitle(panel, "3. Programming Concepts");
    addText(panel,
        "Programming involves writing instructions that a computer can execute. " +
        "Different languages have different characteristics. Object-Oriented Programming (OOP) uses principles " +
        "like encapsulation, inheritance, and polymorphism to structure code effectively.");
    addSpace(panel, 10);

    addBullet(panel, "Compiled Languages: Converted to machine code before running (e.g., C++).");
    addBullet(panel, "Interpreted Languages: Executed line by line (e.g., Python, JavaScript).");
    addBullet(panel, "OOP: Organizes code using objects, making it modular and reusable.");
    addSpace(panel, 10);

    addExample(panel, "Example Question:", "What is polymorphism in OOP?");
    addText(panel, "Answer: Polymorphism allows a single interface to represent multiple implementations. " +
                      "For instance, different objects can respond to the same method in different ways.");
    addSpace(panel, 20);

    // ===== Databases =====
    addSubtitle(panel, "4. Databases");
    addText(panel,
        "Databases store and organize data for efficient retrieval and management. " +
        "Primary keys uniquely identify records, while NoSQL databases like MongoDB handle unstructured data.");
    addSpace(panel, 10);

    addBullet(panel, "Primary Key: A unique identifier for each record in a database.");
    addBullet(panel, "SQL Databases: Use structured queries to manage data (e.g., MySQL, PostgreSQL).");
    addBullet(panel, "NoSQL Databases: Handle flexible, unstructured data (e.g., MongoDB).");
    addSpace(panel, 10);

    addExample(panel, "Example Question:", "What is a primary key in a database?");
    addText(panel, "Answer: A primary key is a unique identifier for each record, ensuring no two records are the same.");
    addSpace(panel, 20);

    // ===== Web & Networking =====
    addSubtitle(panel, "5. Web and Networking Basics");
    addText(panel,
        "Understanding how computers communicate over networks is essential. " +
        "APIs (Application Programming Interfaces) allow different software systems to interact. " +
        "Web technologies like HTML structure web pages, and secure protocols like HTTPS protect data online.");
    addSpace(panel, 10);

    addBullet(panel, "API: Enables communication between software systems.");
    addBullet(panel, "HTML: Standard language for creating web pages.");
    addBullet(panel, "HTTPS: Secure protocol for transmitting data online (default port 443).");
    addSpace(panel, 10);

    addExample(panel, "Example Question:", "What does API stand for?");
    addText(panel, "Answer: Application Programming Interface. It allows different software applications to communicate.");
    addExample(panel, "Example Question:", "What is the default port for HTTPS?");
    addText(panel, "Answer: 443. HTTPS uses this port to provide secure communication over the Internet.");
    addSpace(panel, 20);

    addText(panel,
        "By completing this lesson, you have learned about data structures, algorithms, programming principles, " +
        "databases, and basic web concepts, forming the foundation for building and understanding software.");

addSubtitle(panel, "Quick Test: Computer");
                    addText(panel, "Try these questions! Select an answer and click 'Check Answers' to see your score and corrections.");
                    addSpace(panel, 20);

                    
                    addSubtitle(panel, "Quick Test : Computer");
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
    addLessonTitle(panel, "Lesson 3: Advanced Computer Science Concepts");

    addText(panel, 
        "In this lesson, we explore advanced topics in computer science, including algorithms, functional programming, type systems, " +
        "and theoretical foundations. Mastery of these concepts is essential for software engineering, research, and high-level problem solving.");
    addSpace(panel, 20);

    // ===== Advanced Algorithms =====
    addSubtitle(panel, "1. Advanced Algorithms");
    addText(panel,
        "Algorithms solve problems efficiently. More complex algorithms address performance, optimization, and correctness. " +
        "Understanding time complexity and algorithm design is crucial for advanced computing tasks.");
    addSpace(panel, 10);

    addBullet(panel, "Merge Sort: Efficient sorting algorithm with O(n log n) complexity.");
    addBullet(panel, "Dijkstra's Algorithm: Finds shortest paths in weighted graphs.");
    addBullet(panel, "Banker's Algorithm: Prevents deadlocks in concurrent systems.");
    addBullet(panel, "Memoization: Stores results of expensive function calls to avoid recomputation.");
    addSpace(panel, 10);

    addExample(panel, "Example Question:", "What is memoization?");
    addText(panel, "Answer: Memoization stores the results of expensive computations, so repeated calls can use the stored results instead of recalculating.");
    addExample(panel, "Example Question:", "Which algorithm prevents deadlock?");
    addText(panel, "Answer: Banker's Algorithm. It ensures safe allocation of resources to prevent deadlock in concurrent systems.");
    addSpace(panel, 20);

    // ===== Functional Programming =====
    addSubtitle(panel, "2. Functional Programming");
    addText(panel,
        "Functional programming focuses on writing pure functions, avoiding side effects, and using recursion for iteration. " +
        "It also includes advanced structures like monads, functors, and continuations to manage computation in a predictable way.");
    addSpace(panel, 10);

    addBullet(panel, "Pure Function: Always produces the same output for the same input and has no side effects.");
    addBullet(panel, "Monad: A structure that manages computations, often for handling side effects.");
    addBullet(panel, "Functor: A mapping between categories that preserves structure.");
    addBullet(panel, "Continuation-Passing Style: A programming style where control is passed explicitly using continuations.");
    addSpace(panel, 10);

    addExample(panel, "Example Question:", "What is a monad in functional programming?");
    addText(panel, "Answer: A monad is a programming structure that allows chaining computations in a predictable way while managing effects like state or I/O.");
    addSpace(panel, 20);

    // ===== Recursion and Theoretical Concepts =====
    addSubtitle(panel, "3. Recursion and Theoretical Concepts");
    addText(panel,
        "Recursion occurs when a function calls itself. Understanding recursion, tail recursion optimization, and theoretical topics like the Church-Turing thesis " +
        "or the halting problem is essential for advanced computer science and computational theory.");
    addSpace(panel, 10);

    addBullet(panel, "Recursion: A function calling itself to solve a problem.");
    addBullet(panel, "Tail Recursion Optimization: Reuses stack frames to improve performance in recursive calls.");
    addBullet(panel, "Church-Turing Thesis: All computable functions can be computed by a Turing machine.");
    addBullet(panel, "Halting Problem: Determines whether a program will eventually stop or run forever.");
    addSpace(panel, 10);

    addExample(panel, "Example Question:", "What is tail recursion optimization?");
    addText(panel, "Answer: It is a technique that reuses stack frames for recursive calls to prevent stack overflow and improve efficiency.");
    addExample(panel, "Example Question:", "What is the halting problem?");
    addText(panel, "Answer: The halting problem asks whether it is possible to determine if a program will finish running or continue indefinitely.");
    addSpace(panel, 20);

    // ===== Type Systems and Models =====
    addSubtitle(panel, "4. Type Systems and Computational Models");
    addText(panel,
        "Type systems define how programming languages classify values and expressions. " +
        "Linear, dependent, and functional type systems help prevent errors and manage resources. " +
        "Models like the actor model support concurrent computation, while structures like zippers help traverse data efficiently.");
    addSpace(panel, 10);

    addBullet(panel, "Linear Types: Track resource usage to prevent duplication or loss.");
    addBullet(panel, "Dependent Types: Types that depend on values, enabling more precise checks at compile-time.");
    addBullet(panel, "Actor Model: Supports concurrent computations using independent actors communicating via messages.");
    addBullet(panel, "Zipper Data Structure: Allows efficient traversal and updates of hierarchical data like trees.");
    addSpace(panel, 10);

    addExample(panel, "Example Question:", "Which is a linear type system?");
    addText(panel, "Answer: A linear type system tracks how values are used to ensure resources are not duplicated or lost.");
    addExample(panel, "Example Question:", "What is a zipper data structure?");
    addText(panel, "Answer: A zipper allows efficient traversal and updates of trees by keeping track of the context.");
    addSpace(panel, 20);

    addText(panel,
        "By completing this lesson, you have explored advanced computer science topics including algorithms, functional programming, recursion, theoretical computation, " +
        "type systems, and models for concurrency. These concepts provide a strong foundation for research, software design, and advanced programming challenges.");
        addSubtitle(panel, "Quick Test: Computer");
                    addText(panel, "Try these questions! Select an answer and click 'Check Answers' to see your score and corrections.");
                    addSpace(panel, 20);

    addSpace(panel, 25);
                    addSubtitle(panel, "Quick Test : Computer");
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